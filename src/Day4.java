import java.util.Arrays;
import java.util.List;

public class Day4 {

    public static long part1(List<String> input) {
        return input.stream()
                .map(in -> new Passport(in))
                .filter(passport -> passport.hasAllFields())
                .count();
    }

    public static long part2(List<String> input) {
        return input.stream()
                .map(in -> new Passport(in))
                .filter(passport -> passport.hasAllFields() && passport.hasAllValidFields())
                .count();
    }

    private static class Passport {
        List<String> fields;
        String birthYear;
        String issueYear;
        String expirationYear;
        String height;
        String hairColor;
        String eyeColor;
        String passportId;
        String countryId;

        public Passport(String input) {
            fields = Arrays.asList(input.split("[\\s]"));
            birthYear = getValue("byr");
            issueYear = getValue("iyr");
            expirationYear = getValue("eyr");
            height = getValue("hgt");
            hairColor = getValue("hcl");
            eyeColor = getValue("ecl");
            passportId = getValue("pid");
            countryId = getValue("cid");
        }

        private String getValue(String fieldName) {
            for(String field : fields) {
                String[] keyValue = field.split(":");
                if(fieldName.equals(keyValue[0])) {
                    return keyValue[1];
                }
            }
            return null;
        }

        private boolean hasAllFields() {
            return birthYear != null
                    && issueYear != null
                    && expirationYear != null
                    && height != null
                    && hairColor != null
                    && eyeColor != null
                    && passportId != null;
        }

        private boolean hasAllValidFields() {
            return checkRange(birthYear, "\\d{4}", 1920, 2002)
                    && checkRange(issueYear, "\\d{4}", 2010, 2020)
                    && checkRange(expirationYear, "\\d{4}", 2020, 2030)
                    && checkHeight()
                    && hairColor.matches("#([0-9a-f]{6})")
                    && eyeColor.matches("(amb|blu|brn|gry|grn|hzl|oth)")
                    && passportId.matches("\\d{9}");
        }

        private boolean checkHeight() {
            if(height.matches("\\d*(in|cm)")) {
                String unit = height.substring(height.length() - 2);
                int upper = "cm".equals(unit) ? 193 : 76;
                int lower = "cm".equals(unit) ? 150 : 59;
                int intHeight = Integer.parseInt(height.replace(unit, ""));
                return intHeight >= lower && intHeight <= upper;
            }
            return false;
        }

        private boolean checkRange(String field, String regex, int lower, int upper) {
            if(field.matches(regex)) {
                int intField = Integer.parseInt(field);
                return intField >= lower && intField <= upper;
            }
            return false;
        }
    }

}
