package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister;

    private Button btnBacktologin;

    EditText fullname, phone, user, pass;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        btnRegister = findViewById(R.id.buttonSignup);
        btnBacktologin = findViewById(R.id.backtologin);
        fullname = findViewById(R.id.etfullname);
        phone = findViewById(R.id.etPhone);
        user = findViewById(R.id.etuser);
        pass = findViewById(R.id.etpass);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullName = fullname.getText().toString();
                String Phone = phone.getText().toString();
                String User = user.getText().toString();
                String Password = pass.getText().toString();

                Map<String,Object> user = new HashMap<>();
                user.put("Full Name", FullName);
                user.put("Phone", Phone);
                user.put("User", User);
                user.put("Password", Password);

                db.collection("user")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        btnBacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}