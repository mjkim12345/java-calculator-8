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
                throw new IllegalArgumentException("양수만 입력 가능합니다.");
            }
        }
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
