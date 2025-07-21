package com.example.quizapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules); // Ensure this matches your XML filename

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish(); // Simply goes back to MainActivity
        });
    }
}
