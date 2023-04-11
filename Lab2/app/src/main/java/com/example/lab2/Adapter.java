package com.example.lab2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Employee> arraylist;


    public Adapter(Context context, int layout, ArrayList<Employee> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arraylist = arrayList;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewEmp;
        if(view == null)
            viewEmp = View.inflate(viewGroup.getContext(), this.layout,null);
        else {
            viewEmp = view;
        }
        Employee emp = this.arraylist.get(i);

        TextView name = viewEmp.findViewById(R.id.result_name);
        TextView salary = viewEmp.findViewById(R.id.result_salary);

        name.setText(emp.getName());
        salary.setText(emp.getSalary());

        return viewEmp;
    }
}
