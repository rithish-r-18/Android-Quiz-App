package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestionNumber, tvQuestion, tvTimer;
    Button btnOpt1, btnOpt2, btnOpt3, btnOpt4, btnSubmit;

    List<Question> questions;
    int currentIndex = 0;
    int score = 0;
    CountDownTimer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        btnOpt1 = findViewById(R.id.btnOption1);
        btnOpt2 = findViewById(R.id.btnOption2);
        btnOpt3 = findViewById(R.id.btnOption3);
        btnOpt4 = findViewById(R.id.btnOption4);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setVisibility(View.GONE); // Hide submit at start

        String topic = getIntent().getStringExtra("topic");
        loadQuestionsFromJson(topic);

        if (questions != null && questions.size() > 0) {
            Collections.shuffle(questions);
            questions = questions.subList(0, Math.min(10, questions.size()));
            showQuestion();
        } else {
            Toast.makeText(this, "No questions found", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("total", questions.size());
            intent.putExtra("topic", getIntent().getStringExtra("topic")); // ✅ Add this line here
            startActivity(intent);
            finish();
        });

    }

    private void showQuestion() {
        if (currentIndex >= questions.size()) {
            btnSubmit.setVisibility(View.VISIBLE);
            return;
        }

        Question q = questions.get(currentIndex);

        tvQuestionNumber.setText("Question " + (currentIndex + 1) + " of " + questions.size());
        tvQuestion.setText(q.getQuestion());

        List<String> options = new ArrayList<>(q.getOptions());
        Collections.shuffle(options);

        btnOpt1.setText(options.get(0));
        btnOpt2.setText(options.get(1));
        btnOpt3.setText(options.get(2));
        btnOpt4.setText(options.get(3));

        setClickListener(btnOpt1, options.get(0), q.getAnswer());
        setClickListener(btnOpt2, options.get(1), q.getAnswer());
        setClickListener(btnOpt3, options.get(2), q.getAnswer());
        setClickListener(btnOpt4, options.get(3), q.getAnswer());

        btnSubmit.setVisibility(View.GONE); // Hide submit unless last
        startTimer();
    }

    private void setClickListener(Button button, String selected, String answer) {
        button.setOnClickListener(v -> {
            timer.cancel();
            if (selected.equals(answer)) score++;
            currentIndex++;
            showQuestion();
        });
    }

    private void startTimer() {
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time left: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                currentIndex++;
                showQuestion();
            }
        }.start();
    }

    private void loadQuestionsFromJson(String topic) {
        try {
            InputStream is = getAssets().open(topic + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Question>>() {}.getType();
            questions = gson.fromJson(json, listType);
        } catch (IOException e) {
            e.printStackTrace();
            questions = new ArrayList<>();
        }
    }
}
