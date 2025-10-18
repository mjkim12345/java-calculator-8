package calculator.domain;

import java.util.List;

public class Numbers {
    private List<Integer> numbers;
    private int numberSum;

    public Numbers(List<Integer> numbers) {
        this.numbers = numbers;
        numberSum = sum(numbers);
    }

    private int sum(List<Integer> numbers) {
        int result = 0;
        for (Integer number : numbers) {
            result += number;
        }
        return result;
    }
}
