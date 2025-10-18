package calculator.controller;

import calculator.domain.Numbers;
import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.List;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public CalculatorController(InputView inputView, OutputView outputView, CalculatorService calculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculatorService = calculatorService;
    }

    public void run() {
        String input = inputView.input();
        List<Integer> validatedNumbers = calculatorService.validateInputString(input);
        Numbers numbers = new Numbers(validatedNumbers);
        outputView.output(numbers.sum(validatedNumbers));
    }


}
