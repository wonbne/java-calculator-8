package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class Calculator {
    private static final String DEFAULT_DELIMITER = ",|:";

    public int add(String input){
        int emptyCheck = handleEmptyInput(input);
        if (emptyCheck != -1) return emptyCheck;

        String delimiter = getDelimiter(input);
        String numbers = getNumbersPart(input);

        String[] tokens = splitByDelimiter(numbers, delimiter);

        //임시 리턴값
        return -1;
    }

    //빈 문자열 처리
    private int handleEmptyInput(String input) {
        if (input == null || input.isEmpty()) return 0;
        return -1;
    }

    //구분자 처리
    private String getDelimiter(String input) {
        String delimiter = DEFAULT_DELIMITER;
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\n");
            delimiter += "|" + Pattern.quote(input.substring(2, delimiterIndex));
        }
        return delimiter;
    }

    //숫자 부분 추출
    private String getNumbersPart(String input) {
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\n");
            return input.substring(delimiterIndex + 1);
        }
        return input;
    }

    private String[] splitByDelimiter(String input, String delimiter) {
        return input.split(delimiter);
    }
}
