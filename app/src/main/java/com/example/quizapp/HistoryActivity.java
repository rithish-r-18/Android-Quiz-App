package com.example.quizapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<HistoryEntry> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rvHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyList = loadHistory();
        adapter = new HistoryAdapter(this,historyList);
        recyclerView.setAdapter(adapter);
    }

    private List<HistoryEntry> loadHistory() {
        SharedPreferences prefs = getSharedPreferences("quiz_history", MODE_PRIVATE);
        String json = prefs.getString("history_list", "[]");
        Type type = new TypeToken<List<HistoryEntry>>() {}.getType();
        return new Gson().fromJson(json, type);
    }
}
