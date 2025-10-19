package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {
    public int add(String input){
        return handleEmptyInput(input);
    }

    private int handleEmptyInput(String input) {
        if (input == null || input.isEmpty()) return 0;
        return -1;
    }
}
