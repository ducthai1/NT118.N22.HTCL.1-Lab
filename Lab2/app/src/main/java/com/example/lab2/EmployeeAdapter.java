package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private Context context;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        super(context, 0, employees);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Employee employee = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.etName);
        TextView textViewSalary = convertView.findViewById(R.id.etSalary);
        TextView textViewTotalSalary = convertView.findViewById(R.id.lvResult);

        textViewName.setText(employee.getName());
        textViewSalary.setText(String.format(Locale.getDefault(), "%.2f", employee.getSalary()));
        textViewTotalSalary.setText(String.format(Locale.getDefault(), "%.2f", employee.getTotalSalary()));

        return convertView;
    }
}