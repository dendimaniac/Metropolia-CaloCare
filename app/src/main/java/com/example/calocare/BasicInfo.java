package com.example.calocare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class BasicInfo extends AppCompatActivity {
    private EditText nameTxt, ageTxt;
    private Button nextBtn;
    private RadioGroup gender;
    private boolean onChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info);

        nameTxt = findViewById(R.id.name);
        ageTxt = findViewById(R.id.age);
        nextBtn = findViewById(R.id.next);
        nextBtn.setEnabled(false);
        gender = findViewById(R.id.gender);

        nameTxt.addTextChangedListener(watcher);
        ageTxt.addTextChangedListener(watcher);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onChecked = true;
            }
        });
    }

    private final TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (nameTxt.getText().toString().length() == 0 || ageTxt.getText().toString().length() == 0 || onChecked == false) {
                nextBtn.setEnabled(false);
            } else {
                nextBtn.setEnabled(true);
            }
        }
    };
}
