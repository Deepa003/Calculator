package com.example.deepa.calculatorapp.Utils;

import com.example.deepa.calculatorapp.Model.Operand;

public class Calculator {
    public String add(Operand firstOperand, Operand secondOperand) {
        double result = getValue(firstOperand) + getValue(secondOperand);
        return formatResult(result);
    }

    public String subtract(Operand firstOperand, Operand secondOperand) {
        double result = getValue(firstOperand) - getValue(secondOperand);
        return formatResult(result);
    }

    public String multiply(Operand firstOperand, Operand secondOperand) {
        double result = getValue(firstOperand) * getValue(secondOperand);
        return formatResult(result);
    }

    public String divide(Operand firstOperand, Operand secondOperand) {
        double result = getValue(firstOperand) / getValue(secondOperand);
        return formatResult(result);
    }

    private double getValue(Operand operand) {
        try {
            return Double.valueOf(operand.getValue());
        }catch (NumberFormatException e){
            return 0;
        }
    }

    private String formatResult(Double res) {
        double digits = Math.pow(10, Operand.MAX_DECIMAL_DIGITS);
        res = Math.round(res * digits) / digits;

        String result = Double.toString(res);
        String decimals = result.substring(0, result.indexOf("."));
        String fractionals = result.substring(result.indexOf(".") + 1);

        while (fractionals.length() > 0 && fractionals.substring(fractionals.length() - 1).equals("0")) {
            fractionals = fractionals.substring(0, fractionals.length() - 1);
        }

        if (fractionals.length() > 0) {
            return decimals + "." + fractionals;
        } else {
            return decimals;
        }
    }
}
