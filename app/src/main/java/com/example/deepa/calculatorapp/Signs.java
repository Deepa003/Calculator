package com.example.deepa.calculatorapp;

import com.example.deepa.calculatorapp.Model.Operator;

public class Signs {
    interface View {

        void displayOperand(String calculation);

        void displayOperator(String operator);
    }

    interface Presenter {

        void clearCalculation();

        Operator getOperator();

        void appendValue(String value);

        void appendOperator(String operator);

        void performCalculation();
    }
}
