import java.util.List;

public class Day3 {

    public static long part1(List<String> input) {
        return countTrees(input, 3, 1);
    }

    public static long part2(List<String> input) {
        long a = countTrees(input, 1, 1);
        long b = countTrees(input, 3, 1);
        long c = countTrees(input, 5, 1);
        long d = countTrees(input, 7, 1);
        long e = countTrees(input, 1, 2);
        return a * b * c * d * e;
    }

    private static long countTrees(List<String> input, int right, int down) {
        long nrTrees = 0;
        int xPosition = 0;
        for(int i = 0; i <= input.size() - down; i += down) {
            String row = input.get(i);
            if(xPosition >= row.length()) {
                xPosition = xPosition - row.length();
            }
            char symbol = row.toCharArray()[xPosition];
            if(symbol == '#') {
                nrTrees++;
            }
            xPosition = xPosition + right;
        }
        return nrTrees;
    }

}
