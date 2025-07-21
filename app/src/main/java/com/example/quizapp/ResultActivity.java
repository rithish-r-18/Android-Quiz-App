package com.example.quizapp;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvScore, tvMessage;
    Button btnRestart, btnShare;

    int score;
    int total;
    int percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        tvMessage = findViewById(R.id.tvMessage);
        btnRestart = findViewById(R.id.btnRestart);
        btnShare = findViewById(R.id.btnShare);

        // Receive score & total from QuizActivity
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 10);
        percent = (score * 100) / total;

        tvScore.setText("Your Score: " + score + "/" + total);

        // Show feedback message
        if (percent == 100) {
            tvMessage.setText("🎉 Excellent! You're a quiz master!");
        } else if (percent >= 80) {
            tvMessage.setText("👏 Great job! You did very well.");
        } else if (percent >= 50) {
            tvMessage.setText("👍 Good effort. Keep practicing!");
        } else {
            tvMessage.setText("🙂 Don’t worry. Try again to improve!");
        }

        // ✅ Save History
        String topic = getIntent().getStringExtra("topic"); // Ensure you pass "topic" from QuizActivity
        saveHistory(topic != null ? topic : "Unknown", score, total);

        // Restart
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Share Score
        btnShare.setOnClickListener(v -> {
            String message = "I scored " + score + " out of " + total + " in the Quiz App! 📚💯\n" +
                    "Try it yourself and test your knowledge!";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz Score");
            shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(shareIntent, "Share your score via"));
        });
    }

    // ✅ Save to SharedPreferences as JSON
    private void saveHistory(String topic, int score, int total) {
        SharedPreferences prefs = getSharedPreferences("quiz_history", MODE_PRIVATE);
        Gson gson = new Gson();

        String existingJson = prefs.getString("history_list", "[]");
        Type type = new TypeToken<List<HistoryEntry>>() {}.getType();
        List<HistoryEntry> history = gson.fromJson(existingJson, type);
        if (history == null) history = new ArrayList<>();

        String timestamp = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(new Date());
        history.add(0, new HistoryEntry(topic, score, total, timestamp)); // Add newest first

        prefs.edit().putString("history_list", gson.toJson(history)).apply();
    }
}
