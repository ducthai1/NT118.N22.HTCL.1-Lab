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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etSalary;
    private Button btnCalculate;
    private ListView lvResult;
    private EmployeeListAdapter employees;

    private List<Employee> EmployeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        btnCalculate = findViewById(R.id.btnCalculate);
        lvResult = findViewById(R.id.lvResult);
        employees = new EmployeeListAdapter(this, R.layout, employees);
        lvResult.setAdapter(employees);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int salary = Integer.parseInt(etSalary.getText().toString());

//                Employee employee = new Employee(name, salary);
//                employees.add(employee);
//
//                ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(MainActivity.this, android.R.layout.simple_list_item_1, employees);
//
//                lvResult.setAdapter(adapter);
//                lvResult.deferNotifyDataSetChanged();

                if (!name.isEmpty() && !salary.isEmpty()) {
                    double salary = Double.parseDouble(salaryString);
                    Employee employee = new Employee(name, salary);
                    mEmployeeList.add(employee);
                    mEmployeeListAdapter.notifyDataSetChanged();

                    mNameEditText.setText("");
                    mSalaryEditText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
//                double salary = Double.parseDouble(etSalary.getText().toString());
//                double totalSalary = salary * 2; // Tính lương = lương cơ bản * 2
//                String result = name + ": " + totalSalary; // Chuỗi kết quả

                // Hiển thị kết quả lên ListView
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(R.layout.list_view, new String[]{result});
//                lvResult.setAdapter(adapter);
            }
        });
    }
}