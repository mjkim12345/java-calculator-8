package calculator;


import calculator.domain.Numbers;
import calculator.service.CalculatorService;
import calculator.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();
    private final InputView inputView = new InputView();

    @Test
    void 기본_구분자로_숫자_추출() {
        List<Integer> nums1 = calculatorService.parseNumbers("1,2,3");
        List<Integer> nums2 = calculatorService.parseNumbers("1,2:3");

        assertThat(nums1).isEqualTo(List.of(1, 2, 3));
        assertThat(nums2).isEqualTo(List.of(1, 2, 3));

        System.out.println(nums1);
        System.out.println(nums2);

    }

    @Test
    void 커스텀_구분자로_숫자_추출() {

        List<Integer> nums1 = calculatorService.parseNumbers("//;\\n1;2;3");  // Console 에서 들어오면 \\n 이런식으로 들어옴

        assertThat(nums1).isEqualTo(List.of(1, 2, 3));

        System.out.println(nums1);
    }

    @Test
    void 숫자_리스트_합계_커스텀_구분자() {
        List<Integer> nums1 = calculatorService.parseNumbers("//;\\n1;2;3");
        Numbers numbers = new Numbers(nums1);
        int result = 6;

        assertThat(numbers.sum()).isEqualTo(result);
    }

    @Test
    void 커스텀_구분자_선언_형식이_잘못된_경우_예외() {
        assertThatThrownBy(() -> calculatorService.parseNumbers("//;1;2;3"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> calculatorService.parseNumbers("////n1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_길이가_1이_아니면_예외() {
        assertThatThrownBy(() -> calculatorService.parseNumbers("//;;\\n1;2;3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자가_숫자이면_예외() {
        assertThatThrownBy(() -> calculatorService.parseNumbers("//1\\n213"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_정의_예외() {
        assertThatThrownBy(() -> calculatorService.parseNumbers("3//;\n3;2;1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_없을_때_기본_구분자_외_문자_있으면_예외() {
        assertThatThrownBy(() -> calculatorService.parseNumbers("1;2;3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음수_포함_시_예외() {
        List<Integer> nums1 = calculatorService.parseNumbers("1:-1:3");
        assertThatThrownBy(() -> new Numbers(nums1))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
