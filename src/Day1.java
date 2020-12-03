import java.util.List;

public class Day1 {

    public static int part1(List<Integer> input) {
        for(int i = 0; i < input.size(); i++) {
            Integer inputToCheck = input.get(i);
            for(int j = i + 1; j < input.size(); j++) {
                if(inputToCheck + input.get(j) == 2020) {
                    return inputToCheck * input.get(j);
                }
            }
        }
        return -1;
    }

    public static int part2(List<Integer> input) {
        for(int i = 0; i < input.size(); i++) {
            Integer input1 = input.get(i);
            for(int j = i + 1; j < input.size(); j++) {
                Integer input2 = input.get(j);
                for(int k = j + 1; k < input.size(); k++) {
                    Integer input3 = input.get(k);
                    int total = input1 + input2 + input3;
                    if(total == 2020) {
                        return input1 * input2 * input3;
                    }
                }
            }
        }
        return -1;
    }

}
