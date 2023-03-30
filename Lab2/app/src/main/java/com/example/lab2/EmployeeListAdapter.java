package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    private Context mContext;
    private int mResource;

    public EmployeeListAdapter(Context context, int resource, List<Employee> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.etName);
        TextView salaryTextView = convertView.findViewById(R.id.etSalary);

        Employee employee = getItem(position);

        nameTextView.setText(employee.getName());
        salaryTextView.setText(String.valueOf(employee.getSalary()));

        return convertView;
    }
}
