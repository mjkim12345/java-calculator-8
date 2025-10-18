package calculator;

import calculator.controller.CalculatorController;
import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CalculatorService calculatorService = new CalculatorService();

        CalculatorController calculatorController = new CalculatorController(inputView, outputView, calculatorService);
        calculatorController.run();
    }
}
