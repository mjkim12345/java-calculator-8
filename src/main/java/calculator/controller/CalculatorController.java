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
        try {
            String input = inputView.input();
            List<Integer> numberList = calculatorService.parseNumbers(input);
            Numbers numbers = new Numbers(numberList);
            outputView.output(numbers.sum());
        } catch (IllegalArgumentException e){
            outputView.printError("잘못된 입력입니다. " + e.getMessage());
            throw e;
        }
    }
}
