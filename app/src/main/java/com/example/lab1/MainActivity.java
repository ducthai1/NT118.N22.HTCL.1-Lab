package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.listcolor);

        String[] itemList = new String[]{
                "",
                "",
                "",
                "",
                "",
                ""
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                switch ( position ) {
                    case  0:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_red_light
                        ));
                        break;
                    case  1:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_orange_light
                        ));
                        break;
                    case  2:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_green_light
                        ));
                        break;
                    case  3:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_blue_bright
                        ));
                        break;
                    case  4:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_green_dark
                        ));
                        break;
                    case  5:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_orange_dark
                        ));
                        break;
                    case  6:
                        view.setBackgroundColor(getResources().getColor(
                                android.R.color.holo_purple
                        ));
                        break;
                    default:
                }
                while (position<6)
                {
                    view.setMinimumHeight(300);
                    position++;
                }
                return view;
            }
        };

        listView.setAdapter(arrayAdapter);
    }

}