package calculator;


import calculator.service.CalculatorService;
import calculator.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();
    private final InputView inputView = new InputView();

    @Test
    void 기본_구분자로_숫자_추출() {
        List<Integer> nums1 = calculatorService.parseNumbers("1,2,3");
        List<Integer> nums2 = calculatorService.parseNumbers("1,2:3");

        Assertions.assertThat(nums1).isEqualTo(List.of(1, 2, 3));
        Assertions.assertThat(nums2).isEqualTo(List.of(1, 2, 3));

        System.out.println(nums1);
        System.out.println(nums2);

    }

    @Test
    @DisplayName("커스텀 구분자로 숫자 추출")
    void custom() {

        List<Integer> nums1 = calculatorService.parseNumbers("//;\\n1;2;3");  // Console 에서 들어오면 \\n 이런식으로 들어옴

        Assertions.assertThat(nums1).isEqualTo(List.of(1, 2, 3));

        System.out.println(nums1);
    }
}
