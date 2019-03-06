package com.example.calocare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {
    private Spinner spin;
    private TextView selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        selection = findViewById(R.id.selection);
        //Lấy đối tượng Spinner ra
        spin= findViewById(R.id.spinner1);
        //Gán Data source (arr) vào Adapter
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        /*
        // Optional: Sau khi click xong thi lam gi tiep theo?  SaveReferrence or whatever
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Code is here;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Code is here
            }
        });*/
    }
}
