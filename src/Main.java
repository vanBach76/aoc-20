import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("resources/input_day2.txt"));

//        List<Integer> intInput = Arrays.asList(input.split("\r\n")).stream()
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());

        List<String> stringInput = Arrays.asList(input.split("\r\n"));

        long resultPart1 = Day2.part1(stringInput);
        long resultPart2 = Day2.part2(stringInput);
        System.out.println("Result part 1 was: " + resultPart1);
        System.out.println("Result part 2 was: " + resultPart2);
    }
}
