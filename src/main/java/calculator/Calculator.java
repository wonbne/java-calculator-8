package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Calculator {
    private static final String DEFAULT_DELIMITER = ",|:";

    public int add(String input){
        int emptyCheck = handleEmptyInput(input);
        if (emptyCheck != -1) return emptyCheck;

        // 커스텀 구분자와 숫자 문자열 추출
        String[] result = parseDelimiterAndNumbers(input);
        String delimiter = result[0];
        String numbersPart = result[1];

        String[] tokens = numbersPart.split(delimiter);

        return sumTokens(tokens);
    }

    // 빈 문자열 처리
    private int handleEmptyInput(String input) {
        if (input == null || input.isEmpty()) return 0;
        return -1;
    }

    // 커스텀 구분자와 숫자 문자열을 동시에 처리
    private String[] parseDelimiterAndNumbers(String input) {
        String delimiter = DEFAULT_DELIMITER;
        String numbersPart = input;

        if (input.startsWith("//")) {
            Matcher m = Pattern.compile("//(.)\\\\n(.*)").matcher(input);
            if (!m.find()) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
            }
            delimiter += "|" + Pattern.quote(m.group(1));
            numbersPart = m.group(2);
        }

        return new String[]{delimiter, numbersPart};
    }

    // 합계 계산
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
