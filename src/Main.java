import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("resources/input_day3.txt"));
        String separator = "\r\n\r\n";
//        List<Integer> intInput = Arrays.asList(input.split("\r\n")).stream()
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());

        List<String> stringInput = Arrays.asList(input.split(separator));

        long resultPart1 = Day4.part2(stringInput);
        System.out.println("Result part 1 was: " + resultPart1);
    }
}
