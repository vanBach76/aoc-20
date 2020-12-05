import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        String input = Files.readString(Path.of("resources/input_day5.txt"));
        String separator = "\r\n";
//        List<Integer> intInput = Arrays.asList(input.split("\r\n")).stream()
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());

        List<String> stringInput = Arrays.asList(input.split(separator));

        int result = Day5.part2(stringInput);
        System.out.println("Result was: " + result);
    }
}
