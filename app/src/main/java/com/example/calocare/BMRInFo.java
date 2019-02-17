package com.example.calocare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BMRInFo extends AppCompatActivity {
    private EditText heightTxt;
    private EditText weightTxt;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr_info);
        this.setTitle("Physical Info");

        heightTxt = findViewById(R.id.txt_height);
        weightTxt = findViewById(R.id.txt_weight);
        nextBtn = findViewById(R.id.next);

        heightTxt.addTextChangedListener(watcher);
        weightTxt.addTextChangedListener(watcher);

        heightTxt.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        weightTxt.setTransformationMethod(new NumericKeyBoardTransformationMethod());
    }

    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    private final TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(heightTxt.getText().toString().trim())
                    || TextUtils.isEmpty(weightTxt.getText().toString().trim())) {
                nextBtn.setEnabled(false);
            } else {
                nextBtn.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };
}
