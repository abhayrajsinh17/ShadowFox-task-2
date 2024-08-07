package com.example.basiccalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView solutionTv;
    private TextView resultTv;

    private String currentExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solutionTv = findViewById(R.id.solution_tv);
        resultTv = findViewById(R.id.result_tv);

        setUpButtons();
    }

    private void setUpButtons() {
        int[] buttonIds = {
                R.id.btn_c, R.id.btn_open_bracket, R.id.btn_close_bracket, R.id.btn_divide,
                R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_multiply,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_plus,
                R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_minus,
                R.id.btn_ac, R.id.btn_0, R.id.btn_dot, R.id.btn_equals
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentExpression = currentExpression.isEmpty() ? "" : currentExpression.substring(0, currentExpression.length() - 1);
                break;
            case "AC":
                currentExpression = "";
                break;
            case "=":
                calculateResult();
                break;
            default:
                currentExpression += buttonText;
                break;
        }

        updateDisplay();
    }

    private void calculateResult() {
        try {
            Expression expression = new ExpressionBuilder(currentExpression).build();
            resultTv.setText(String.valueOf(expression.evaluate()));
        } catch (Exception e) {
            resultTv.setText("Error");
        }
    }

    private void updateDisplay() {
        solutionTv.setText(currentExpression);
    }
}
