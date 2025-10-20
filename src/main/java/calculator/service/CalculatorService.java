package calculator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorService {

    private static final String BASIC_DELIMITER = ",|:";
    private static final String START_CUSTOM_DELIMITER = "//";
    private static final String END_CUSTOM_DELIMITER = "\\n";

    public List<Integer> parseNumbers(String input) {

        if(input.equals("")){
            return List.of();
        }

        String customDelimiter = findCustomDelimiter(input);
        List<Integer> numbers;

        if(customDelimiter == null || customDelimiter.isEmpty()) {
            validateDelimiterPosition(input);
            try {
                numbers = Arrays.stream(input.split(BASIC_DELIMITER))
                        .map(Integer::parseInt)
                        .toList();
                return numbers;
            } catch (Exception e) {
                throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
            }
        }
        String refactoredInput = input.substring(
                input.lastIndexOf(END_CUSTOM_DELIMITER)+2);
        validateDelimiterPosition(refactoredInput);
        numbers = Arrays.stream(refactoredInput.split(BASIC_DELIMITER + "|" + customDelimiter))
                .map(Integer::parseInt)
                .toList();
        return numbers;
    }

    private String findCustomDelimiter(String input) {
        if(input.contains(START_CUSTOM_DELIMITER) && input.contains(END_CUSTOM_DELIMITER)) {
            if(!input.startsWith(START_CUSTOM_DELIMITER)) {
                throw new IllegalArgumentException("커스텀 구분자 정의는 문자열 맨 앞에 와야 합니다.");
            }
            int startIdx = input.indexOf(START_CUSTOM_DELIMITER)+2;
            int endIdx = input.lastIndexOf(END_CUSTOM_DELIMITER);

            if(endIdx <0 || endIdx < startIdx) {
                throw new IllegalArgumentException("커스텀 구분자 사용이 잘못되었습니다.");
            }

            String delimiter = input.substring(startIdx, endIdx);

            if(delimiter.length() != 1){
                throw new IllegalArgumentException("커스텀 구분자의 길이는 1이어야 합니다.");
            }
            if(Character.isDigit(delimiter.charAt(0))) {
                throw new IllegalArgumentException("커스텀 구분자는 문자여야 합니다.");
            }
            return delimiter;
        }
        return null;
    }

    private void validateDelimiterPosition(String input) {
        if(input.isEmpty()){
            return;
        }
        char firstChar = input.charAt(0);
        char lastChar = input.charAt(input.length()-1);

        if(firstChar == ',' || firstChar == ':' || lastChar == ',' || lastChar == ':'){
            throw new IllegalArgumentException("구분자 앞뒤에는 항상 숫자가 있어야 합니다.");
        }
    }
}
