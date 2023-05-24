package com.example.lab4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView result, btnBacktoSignup;
    private EditText etUsername, etPassword;

    private FirebaseFirestore db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etfullname);
        etPassword = findViewById(R.id.etpassword);
        result = (TextView) findViewById(R.id.hashpasslogin);
        db = FirebaseFirestore.getInstance();
        btnLogin = findViewById(R.id.buttonLogin);
        btnBacktoSignup = findViewById(R.id.backtosignup);
        LayoutInflater inflater = getLayoutInflater();
        View layout = getLayoutInflater().inflate(R.layout.custom_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.custom_toast_text);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = etPassword.getText().toString();
//                HashPass(etPassword.getText().toString());
                String username = etUsername.getText().toString();
//                String HashPassLogin = result.getText().toString();


                try {
                    db.collection("user")
                            .whereEqualTo("User", username)
                            .whereEqualTo("Password", AESCrypt.encrypt("myKey", password))
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        QuerySnapshot snapshot= task.getResult();
                                        if (snapshot.isEmpty()) {
                                            // Tài khoản không tồn tại hoặc mật khẩu không chính xác
                                            LayoutInflater inflater = getLayoutInflater();
                                            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
                                            TextView text = (TextView) layout.findViewById(R.id.custom_toast_text);
                                            text.setText("Username or Password is incorrect.");
                                            Toast toast = new Toast(getApplicationContext());
                                            toast.setDuration(Toast.LENGTH_SHORT);
                                            toast.setView(layout);
                                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 40);
                                            toast.show();
                                        } else {
                                            // Tài khoản tồn tại và mật khẩu chính xác, chuyển sang màn hình chính
                                            Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                            startActivity(intent);
                                            finishAffinity();
                                        }
                                    } else {
                                        // Lỗi xảy ra khi truy vấn Firestore
                                        Toast.makeText(LoginActivity.this, "Error querying database.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } catch (GeneralSecurityException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnBacktoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });


    }

//    public void HashPass(String password)
//    {
//        try {
//            MessageDigest md = java.security.MessageDigest.getInstance("MD5");
//
//            md.update(password.getBytes());
//            byte[] messageDigest = md.digest();
//
//            StringBuffer MD5Hash = new StringBuffer();
//            for (int i = 0; i < messageDigest.length; i++)
//            {
//                String h = Integer.toHexString(0xFF & messageDigest[i]);
//                while (h.length() < 2)
//                    h = "0" + h;
//                MD5Hash.append(h);
//            }
//            result.setText( MD5Hash);
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//    }

}