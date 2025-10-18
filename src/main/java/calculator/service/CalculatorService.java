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
            numbers = Arrays.stream(input.split(BASIC_DELIMITER))
                    .map(Integer::parseInt)
                    .toList();
            return numbers;
        }
        String refactoredInput = input.substring(
                input.lastIndexOf(END_CUSTOM_DELIMITER)+2);
        numbers = Arrays.stream(refactoredInput.split(BASIC_DELIMITER + "|" + customDelimiter))
                .map(Integer::parseInt)
                .toList();
        return numbers;
    }

    private String findCustomDelimiter(String input) {
        if(input.contains(START_CUSTOM_DELIMITER) && input.contains(END_CUSTOM_DELIMITER)) {
                int index1 = input.indexOf(START_CUSTOM_DELIMITER)+2;
                int index2 = input.lastIndexOf(END_CUSTOM_DELIMITER);

                StringBuffer sb = new StringBuffer();
                sb.append(input, index1, index2);

                String result = sb.toString();
                return result;
            }
        return null;
    }
}
