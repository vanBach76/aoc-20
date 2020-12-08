import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

    private static List<Bag> bags;

    public static long part1(List<String> input) {
        bags = input.stream().map(Bag::new).collect(Collectors.toList());
        int nrBags = 0;
        for (Bag bag : bags) {
            int i = checkBag(bag);
            if (i > 0) {
                nrBags++;
            }
        }
        return nrBags;
    }

    public static long part2(List<String> input) {
        bags = input.stream().map(Bag::new).collect(Collectors.toList());
        return countContaining( getBag("shiny gold"));
    }

    private static int countContaining(Bag bag) {
        if(bag.containingColors.containsKey("o other")) {
            return 0;
        }
        int directlyContaining = bag.containingColors.values().stream()
                .reduce(0, Integer::sum);
        return directlyContaining +
                bag.containingColors.keySet().stream()
                .map(c -> bag.containingColors.get(c) * countContaining(getBag(c)))
                .reduce(0, Integer::sum);
    }

    private static int checkBag(Bag bag) {
        if(bag.containingColors.containsKey("shiny gold")) {
            return 1;
        }
        if(bag.containingColors.containsKey("o other")) {
            return 0;
        }
        return bag.containingColors.keySet().stream()
                .map(c -> checkBag(getBag(c)))
                .reduce(0, (cur, acc ) -> cur + acc);
    }

    private static Bag getBag(String color) {
        return bags.stream()
                .filter(b -> b.color.equals(color))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find bag of color " + color));
    }


    private static class Bag {

        String color;
        Map<String,Integer> containingColors;

        public Bag(String input) {
            containingColors = new HashMap<>();
            String[] split = input.split(" bags contain ");
            color = split[0];
            String[] contains = split[1].split(",");
            for(String in : contains) {
                String number = in.strip().substring(0, 1);
                int nrBags = number.equals("n") ? 0 : Integer.parseInt(number);
                String bagColor = in.replace(number, "").strip().replaceAll("bags?\\.?", "").strip();
                containingColors.put(bagColor, nrBags);
            }
        }


    }

}
