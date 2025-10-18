package calculator.domain;

import java.util.List;

public class Numbers {
    private final List<Integer> numbers;

    public Numbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        for(Integer number : numbers) {
            if(number < 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int sum(List<Integer> numbers) {
        int total=0;
        for (Integer number : numbers) {
            total += number;
        }
        return total;
    }
}
