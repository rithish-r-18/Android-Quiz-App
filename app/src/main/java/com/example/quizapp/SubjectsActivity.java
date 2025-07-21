package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SubjectsActivity extends AppCompatActivity {

    Button btnJava, btnPython, btnSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        btnJava = findViewById(R.id.btnJava);
        btnPython = findViewById(R.id.btnPython);
        btnSQL = findViewById(R.id.btnSQL);

        btnJava.setOnClickListener(v -> startQuiz("java"));
        btnPython.setOnClickListener(v -> startQuiz("python"));
        btnSQL.setOnClickListener(v -> startQuiz("sql"));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("topic", topic);
        startActivity(intent);
        finish();
    }
}
