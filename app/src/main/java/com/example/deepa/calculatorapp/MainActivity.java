package com.example.deepa.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deepa.calculatorapp.Model.Operand;
import com.example.deepa.calculatorapp.Utils.Calculator;

public class MainActivity extends AppCompatActivity implements Signs.View, View.OnClickListener {

    private Signs.Presenter mPresenter;

    TextView mCalculationView, mOperatorView;
    Button btn_dot, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_plus, btn_minus, btn_equals, btn_multiply, btn_divide, btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeID();
        onclickUI();

        mPresenter = new Presenter(new Calculator(), this, new Operand(), new Operand());
    }

    private void initializeID() {
        mCalculationView = findViewById(R.id.txtv_display_calculation);
        mOperatorView = findViewById(R.id.txtv_display_operator);
        btn_dot = findViewById(R.id.btn_dot);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_clear = findViewById(R.id.btn_clear);
        btn_equals = findViewById(R.id.btn_equals);
    }

    private void onclickUI() {
        btn_dot.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_equals.setOnClickListener(this);
    }


    @Override
    public void displayOperand(String calculation) {
        mCalculationView.setText(calculation);
    }

    @Override
    public void displayOperator(String operator) {
        if (operator.equalsIgnoreCase("*")){
            mOperatorView.setText("X");
        }
        else if(operator.equalsIgnoreCase("/")){
            mOperatorView.setBackground(getResources().getDrawable(R.drawable.divide));
        }
        else {
            mOperatorView.setText(operator);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_equals:
                mOperatorView.setBackground(null);
                mPresenter.performCalculation();
                break;

            case R.id.btn_clear:
                mOperatorView.setBackground(null);
                mPresenter.clearCalculation();
                break;

            case R.id.btn_0:
                mPresenter.appendValue((String) btn_0.getText());
                break;

            case R.id. btn_1:
                mPresenter.appendValue((String) btn_1.getText());
                break;

            case R.id.btn_2:
                mPresenter.appendValue((String) btn_2.getText());
                break;

            case R.id.btn_3:
                mPresenter.appendValue((String) btn_3.getText());
                break;

            case R.id.btn_4:
                mPresenter.appendValue((String) btn_4.getText());
                break;

            case R.id.btn_5:
                mPresenter.appendValue((String) btn_5.getText());
                break;

            case R.id.btn_6:
                mPresenter.appendValue((String) btn_6.getText());
                break;

            case R.id.btn_7:
                mPresenter.appendValue((String) btn_7.getText());
                break;

            case R.id.btn_8:
                mPresenter.appendValue((String) btn_8.getText());
                break;

            case R.id.btn_9:
                mPresenter.appendValue((String) btn_9.getText());
                break;

            case R.id.btn_dot:
                if(mCalculationView.getText().length()==0){
                    mPresenter.appendValue("0"+(String) btn_dot.getText());
                }else{
                    mPresenter.appendValue((String) btn_dot.getText());
                }
                break;

            case R.id.btn_plus:
                mOperatorView.setBackground(null);
                mPresenter.appendOperator((String)btn_plus.getText());
                break;

            case R.id.btn_minus:
                mOperatorView.setBackground(null);
                mPresenter.appendOperator((String)btn_minus.getText());
                break;

            case R.id.btn_multiply:
                mOperatorView.setBackground(null);
                mPresenter.appendOperator("*");
                break;

            case R.id.btn_divide:
                mOperatorView.setBackground(null);
                mPresenter.appendOperator("/");
                break;

        }
    }
}


