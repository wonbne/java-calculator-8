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

        return sumTokens(tokens);
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
            // "\\n" 문자열로 커스텀 구분자 끝을 찾음
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
            }
            delimiter += "|" + Pattern.quote(input.substring(2, delimiterIndex));
        }
        return delimiter;
    }

    //숫자 부분 추출
    private String getNumbersPart(String input) {
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            return input.substring(delimiterIndex + 2); // "\\n" 길이 2
        }
        return input;
    }

    private String[] splitByDelimiter(String input, String delimiter) {
        return input.split(delimiter);
    }

    //합계 계산
    private int sumTokens(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            if (token.isEmpty()) continue;

            int num;
            try {
                num = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 입력되었습니다: " + token);
            }

            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
            }

            sum += num;
        }
        return sum;
    }
}
