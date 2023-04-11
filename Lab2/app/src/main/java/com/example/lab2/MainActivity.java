package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etSalary;
    Button btnCalc;
    ListView listStaff;

    String name;
    int grossSalary ;
    String net_salary;

    ArrayList<Employee> arrayList = new ArrayList<>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etSalary = findViewById(R.id.et_salary);
        btnCalc = findViewById(R.id.btn_Calc);



        listStaff = findViewById(R.id.list_Staff);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = String.valueOf(etName.getText());

                // Công thức tính net

                grossSalary = Integer.parseInt(String.valueOf(etSalary.getText()));
                int temp_salary = grossSalary-(int)(grossSalary*0.105);

                if(temp_salary<=11000000)
                {
                    net_salary = String.valueOf(temp_salary);
                }
                else {
                    net_salary = String.valueOf(11000000 + (int)((temp_salary - 11000000)*0.95));
                }
                Employee emp = new Employee(name, net_salary);
                arrayList.add(emp);

                adapter = new Adapter(MainActivity.this, R.layout.item_listview, arrayList);
                listStaff.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }
}