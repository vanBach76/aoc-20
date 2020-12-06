import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    public static int part1(List<String> input) {
        int sum = 0;
        for(String in : input) {
            HashSet<Integer> yes = getUnique(in);
            sum += yes.size();
        }
        return sum;
    }

    public static int part2(List<String> input) {
        int sum = 0;
        for(String in : input) {
            HashSet<Integer> unique = getUnique(in);
            List<Integer> answers = in.replace("\\r\\n", "")
                    .chars()
                    .boxed()
                    .collect(Collectors.toList());

            int nrAnswers = in.split("\\r\\n").length;
            long count = unique.stream()
                    .map(a -> Collections.frequency(answers, a))
                    .filter(a -> a == nrAnswers)
                    .count();
            sum += count;

        }
        return sum;
    }

    private static HashSet<Integer> getUnique(String in) {
        var yes = new HashSet<Integer>();
        String line = in.replace("\r\n", "");
        line.chars().forEach(yes::add);
        return yes;
    }

}
