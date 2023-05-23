package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.security.MessageDigestSpi;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnRegister, btnBacktologin;
    TextView result;

    EditText fullname, phone, user, pass;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.hashcode);
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
                HashPass(pass.toString());
                String FullName = fullname.getText().toString();
                String Phone = phone.getText().toString();
                String User = user.getText().toString();
                String Password = pass.getText().toString();
                String emPass = result.getText().toString();

                if (TextUtils.isEmpty(FullName))
                {
                    Toast.makeText(MainActivity.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Phone))
                {
                    Toast.makeText(MainActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(User))
                {
                    Toast.makeText(MainActivity.this, "Please enter your user name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Password))
                {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                } else if (User.length()<6) {
                    Toast.makeText(MainActivity.this, "User name phai dai hon 6 ky tu", Toast.LENGTH_SHORT).show();
                } else if (User.matches(".*\\d.*"))
                {
                    Toast.makeText(MainActivity.this, "User name khong chua ki tu so", Toast.LENGTH_SHORT).show();
                } else if (Password.length()<6)
                {
                    Toast.makeText(MainActivity.this, "Mat khau phai dai hon 6 ky tu", Toast.LENGTH_SHORT).show();
                } else   {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Full Name", FullName);
                    user.put("Phone", Phone);
                    user.put("User", User);
//                user.put("Password", Password);
                    user.put("Password", emPass);


                    db.collection("user")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(MainActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
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



        public void HashPass(String password)
        {
            try {
                MessageDigest md = java.security.MessageDigest.getInstance("MD5");

                md.update(password.getBytes());
                byte[] messageDigest = md.digest();

                StringBuffer MD5Hash = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++)
                {
                    String h = Integer.toHexString(0xFF & messageDigest[i]);
                    while (h.length() < 2)
                        h = "0" + h;
                    MD5Hash.append(h);
                }
                    result.setText( MD5Hash);
                }
                    catch (NoSuchAlgorithmException e)
                    {
                        e.printStackTrace();
                    }
        }
}