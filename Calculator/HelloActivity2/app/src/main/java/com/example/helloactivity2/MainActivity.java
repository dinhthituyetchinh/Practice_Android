package com.example.helloactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView tvResult;

    ArrayList btnNumbers = new ArrayList(Arrays.asList(
        R.id.btn_num_0,
        R.id.btn_num_1,
        R.id.btn_num_2,
        R.id.btn_num_3,
        R.id.btn_num_4,
        R.id.btn_num_5,
        R.id.btn_num_6,
        R.id.btn_num_7,
        R.id.btn_num_8,
        R.id.btn_num_9,
        R.id.btn_dot
    ));

    ArrayList btnOps = new ArrayList(Arrays.asList(
            R.id.btn_plus,
            R.id.btn_sub,
            R.id.btn_div,
            R.id.btn_mul
    ));

    StringBuilder mathExpression = new StringBuilder();
    String lastUserInput = "";

    View.OnClickListener clickOfNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            if (lastUserInput.isEmpty()) {
                lastUserInput = btn.getText().toString();
            } else {
                lastUserInput += btn.getText().toString();
            }

            tvResult.setText(mathExpression.toString() + lastUserInput);
        }
    };

    View.OnClickListener clickOfOps = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            if (lastUserInput != btn.getText().toString()
                    && !isOps(lastUserInput)
            ) {
                mathExpression.append(lastUserInput);
                lastUserInput = "";
            }

            lastUserInput = btn.getText().toString();
            tvResult.setText(mathExpression.toString() + lastUserInput);
        }
    };

    View.OnClickListener clickOfEqual = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double result = 0;

            Log.e(TAG, "user input: " + lastUserInput);
            Log.e(TAG, "user input: " + isOps(lastUserInput));
            Log.e(TAG, "exp: " + mathExpression.toString());

            // Case 1: lần cuối user nhập số
            if(!isOps(lastUserInput)) {
                mathExpression.append(lastUserInput);

                Expression ex = new Expression();
                ex.setExpressionString(mathExpression.toString());
                result = ex.calculate();
            }
            // Case 2: lần cuối user nhập dấu
            else if(isOps(lastUserInput)) {
                // ...
                // Lấy giá trị cuối cùng + dấu + chính nó
                // 12 + = ==> 12 + 12

                Expression ex = new Expression();
                ex.setExpressionString(mathExpression.toString());
                result = ex.calculate();

                Expression exResult = new Expression();
                exResult.setExpressionString(result + lastUserInput + result);
                result = exResult.calculate();
            }


            tvResult.setText(String.valueOf(result));
            mathExpression = new StringBuilder();
            lastUserInput = "";
        }
    };

    protected boolean isOps(String lastUserInput)
    {
        return lastUserInput.equals("+")
                || lastUserInput.equals("-")
                || lastUserInput.equals("*")
                || lastUserInput.equals("/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_relative);
        tvResult = findViewById(R.id.tv_result);
        bindEventClick();
    }

    protected void bindEventClick()
    {
        findViewById(R.id.btn_equal).setOnClickListener(clickOfEqual);

        // Gắn event cho nút số
        for (Object id :
                btnNumbers) {
            findViewById((int) id).setOnClickListener(clickOfNumber);
        }

        for (Object id :
                btnOps) {
            findViewById((int) id).setOnClickListener(clickOfOps);
        }
    }
}