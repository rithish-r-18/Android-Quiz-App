package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStartQuiz, btnRules, btnHistory, btnLogout, btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnRules = findViewById(R.id.btnRules);
        btnHistory = findViewById(R.id.btnHistory);
        btnLogout = findViewById(R.id.btnLogout);
        btnShop = findViewById(R.id.btnShop); // New Shop Books button

        // Start Quiz Button
        btnStartQuiz.setOnClickListener(v -> {
            startActivity(new Intent(this, SubjectsActivity.class));
        });

        // Rules Button
        btnRules.setOnClickListener(v -> {
            startActivity(new Intent(this, RulesActivity.class));
        });

        // History Button
        btnHistory.setOnClickListener(v -> {
            Toast.makeText(this, "History clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HistoryActivity.class));
        });

        // Logout Button
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // ✅ Shop Books Button - now opens your in-app ShoppingActivity
        btnShop.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShoppingActivity.class);
            startActivity(intent);
        });
    }
}
