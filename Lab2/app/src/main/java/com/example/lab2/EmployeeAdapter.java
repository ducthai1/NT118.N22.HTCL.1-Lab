package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;

    private List<Employee> arraylist;

    private int layout;
    public EmployeeAdapter (Context context, int layout, List<Employee> arraylist) {
        this.layout = layout;
        this.arraylist = arraylist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Employee employee = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.etSalary, parent, false);
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