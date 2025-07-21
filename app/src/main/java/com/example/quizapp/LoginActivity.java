package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button LoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  //connects to login layt
        // to intialize the fields
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        LoginBtn = findViewById(R.id.Loginbtn);  // <-- Make sure ID matches XML

        // button logic
        LoginBtn.setOnClickListener(view -> {      // lambda expression .. view -> {.code.}
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.equals("admin") && password.equals("admin")) {
                Toast.makeText(this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                // to navigate to home page
                startActivity(new Intent(this, MainActivity.class));
                finish(); // closes the current page
            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Please Try again", Toast.LENGTH_SHORT).show();
            }
        });

        // register button
        // register button
        Button registerBtn = findViewById(R.id.registerbtn);
        registerBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
    }
