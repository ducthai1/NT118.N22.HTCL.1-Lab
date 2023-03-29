package com.example.lab2;
import com.example.lab2.Employee;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etSalary;
    private Button btnCalculate;
    private ListView lvResult;
    private ArrayList<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        btnCalculate = findViewById(R.id.btnCalculate);
        lvResult = findViewById(R.id.lvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
//                double salary = Double.parseDouble(etSalary.getText().toString());
                int salary = Integer.parseInt(etSalary.getText().toString());

                Employee employee = new Employee(name, salary);
                employees.add(employee);

                ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(MainActivity.this, android.R.layout.simple_list_item_1, employees);
                lvResult.setAdapter(adapter);
//                double totalSalary = salary * 2; // Tính lương = lương cơ bản * 2
//                String result = name + ": " + totalSalary; // Chuỗi kết quả

                // Hiển thị kết quả lên ListView
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(R.layout.list_view, new String[]{result});
//                lvResult.setAdapter(adapter);
            }
        });
    }
}