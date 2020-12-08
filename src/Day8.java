import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

    public static int part1(List<String> input) {
        List<Instruction> instructions = input.stream().map(Instruction::new).collect(Collectors.toList());
        return run(instructions).acc;
    }

    public static int part2(List<String> input) {
        List<Instruction> instructions = input.stream().map(Instruction::new).collect(Collectors.toList());
        int changedIndex = 0;
        Result result = new Result(0, true);
        while(result.looped) {
            boolean isChanged = false;
            while(!isChanged) {
                Instruction instruction = instructions.get(changedIndex);
                if(instruction.type.equals("jmp") || instruction.type.equals("nop")) {
                    instruction.switchType();
                    isChanged = true;
                }
                changedIndex++;
            }
            result = run(instructions);
            instructions.get(changedIndex - 1).switchType();
            instructions.parallelStream().forEach(i -> i.hasRun = false);
        }
        return result.acc;
    }

    private static Result run(List<Instruction> instructions) {
        int acc = 0;
        int index = 0;
        var currentInstr = instructions.get(index);
        while(!currentInstr.hasRun) {
            switch(currentInstr.type) {
                case "acc": {
                    acc += currentInstr.arg;
                    index++;
                    break;
                }
                case "jmp":
                    index += currentInstr.arg;
                    break;
                case "nop": {
                    index++;
                    break;
                }
            }
            if(index >= instructions.size()) {
                break;
            }
            currentInstr.hasRun = true;
            currentInstr = instructions.get(index);
        }
        return new Result(acc, currentInstr.hasRun);
    }

    private static class Result {
        int acc;
        boolean looped;

        public Result(int acc, boolean looped) {
            this.acc = acc;
            this.looped = looped;
        }
    }

    private static class Instruction {

        boolean hasRun;
        String type;
        int arg;

        public Instruction(String input) {
            type = input.substring(0, 3);
            arg = Integer.parseInt(input.split(" ")[1]);
        }

        public void switchType() {
            type = type.equals("jmp") ? "nop" : "jmp";
        }

    }

}
