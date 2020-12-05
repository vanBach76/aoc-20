import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {

    public static int part1(List<String> input) throws Exception {
        int highestSeat = 0;
        List<Integer> rows = IntStream.range(0, 128).boxed().collect(Collectors.toList());
        List<Integer> seats = IntStream.range(0, 8).boxed().collect(Collectors.toList());
        for(String in : input) {
            String rowInstructions = in.substring(0, 7);
            String seatInstructions = in.substring(7);

            int row = search(rows, rowInstructions);
            int seat = search(seats, seatInstructions);

            int seatId = row * 8 + seat;
            if(seatId > highestSeat) {
                highestSeat = seatId;
            }
        }
        return highestSeat;
    }

    public static int part2(List<String> input) throws Exception {
        List<Integer> includedIds = new ArrayList<>();
        List<Integer> rows = IntStream.range(0, 128).boxed().collect(Collectors.toList());
        List<Integer> seats = IntStream.range(0, 8).boxed().collect(Collectors.toList());
        for(String in : input) {
            String rowInstructions = in.substring(0, 7);
            String seatInstructions = in.substring(7);
            int row = search(rows, rowInstructions);
            int seat = search(seats, seatInstructions);
            includedIds.add(row * 8 + seat);
        }
        List<Integer> allIds = IntStream.range(0, 127 * 8 + 8).boxed().collect(Collectors.toList());

        return allIds.stream()
                .filter(id -> !includedIds.contains(id) && includedIds.contains(id +1 ) && includedIds.contains(id -1 ))
                .findFirst()
                .orElse(-1);

    }

    private static int search(List<Integer> numbers, String instructions) throws Exception {
        if(numbers.size() == 1) {
            return numbers.get(0);
        }
        String instruction = instructions.substring(0, 1);
        if(instruction.equals("R") || instruction.equals("B")) {
            return search(numbers.subList(numbers.size() / 2, numbers.size()), instructions.substring(1));
        }
        else if(instruction.equals("L") || instruction.equals("F")) {
            return search(numbers.subList(0, numbers.size() / 2), instructions.substring(1));
        }
        throw new Exception("Invalid instruction: " + instruction);
    }

}
