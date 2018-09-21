package com.example.deepa.calculatorapp;

import com.example.deepa.calculatorapp.Model.Operand;
import com.example.deepa.calculatorapp.Model.Operator;
import com.example.deepa.calculatorapp.Utils.Calculator;

public class Presenter implements Signs.Presenter {

    private Calculator mCalculator;
    private Signs.View mView;

    private Operand mCurrentOperand;
    private Operand mPreviousOperand;
    private Operator mOperator;
    private boolean hasLastInputOperator;
    private boolean hasLastInputEquals;
    private boolean isInErrorState;

    public Presenter(Calculator calculator,
                     Signs.View view,
                     Operand currentOperand,
                     Operand previousOperand) {
        mCalculator = calculator;
        mView = view;

        mCurrentOperand = currentOperand;
        mPreviousOperand = previousOperand;
        resetCalculator();
        updateDisplay();
    }

    @Override
    public void clearCalculation() {
        resetCalculator();
        updateDisplay();
    }

    @Override
    public Operator getOperator() {
        return mOperator;
    }

    @Override
    public void appendValue(String value) {
        if (hasLastInputOperator) {
            mPreviousOperand.setValue(mCurrentOperand.getValue());
            mCurrentOperand.reset();
        } else if (hasLastInputEquals) {
            resetCalculator();
        }

        if (mCurrentOperand.getValue().length() < Operand.MAX_LENGTH) {
            mCurrentOperand.appendValue(value);
            hasLastInputOperator = false;
            hasLastInputEquals = false;
            isInErrorState = false;
            updateDisplay();
        }
    }

    @Override
    public void appendOperator(String operator) {
        if (!isInErrorState) {
            if (mOperator != Operator.EMPTY && !hasLastInputOperator) {
                performCalculation();

                if (isInErrorState) {
                    return;
                }
            }

            mOperator = Operator.getOperator(operator);
            hasLastInputOperator = true;
            updateDisplay();
        }
    }

    @Override
    public void performCalculation() {
        String result = "";

        switch (mOperator) {
            case PLUS:
                result = mCalculator.add(mPreviousOperand, mCurrentOperand);
                break;
            case MINUS:
                result = mCalculator.subtract(mPreviousOperand, mCurrentOperand);
                break;
            case MULTIPLY:
                result = mCalculator.multiply(mPreviousOperand, mCurrentOperand);
                break;
            case DIVIDE:
                if (!mCurrentOperand.getValue().equals(Operand.EMPTY_VALUE)) {
                    result = mCalculator.divide(mPreviousOperand, mCurrentOperand);
                }
                break;
            default:
                result = mCurrentOperand.getValue();
        }

        if (result.equals("") || result.length() > Operand.MAX_LENGTH) {
            switchToErrorState();
        } else {
            mCurrentOperand.setValue(result);
        }

        mPreviousOperand.reset();
        mOperator = Operator.EMPTY;
        hasLastInputEquals = true;
        updateDisplay();
    }

    private void switchToErrorState() {
        mCurrentOperand.setValue(Operand.ERROR_VALUE);
        isInErrorState = true;
    }

    private void resetCalculator() {
        mCurrentOperand.reset();
        mPreviousOperand.reset();
        hasLastInputEquals = false;
        hasLastInputOperator = false;
        isInErrorState = false;
        mOperator = Operator.EMPTY;
    }

    private void updateDisplay() {
        mView.displayOperand(mCurrentOperand.getValue());
        mView.displayOperator(mOperator.toString());
    }
}
