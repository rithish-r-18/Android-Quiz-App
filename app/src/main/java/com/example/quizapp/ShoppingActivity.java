package com.example.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        recyclerView = findViewById(R.id.rvBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        loadBooks();

        adapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
    }

    private void loadBooks() {
        bookList.add(new Book(
                "Java Quiz Mastery",
                R.drawable.javaquiz,
                "https://www.amazon.in/s?k=java+quiz+book"
        ));

        bookList.add(new Book(
                "Python Practice Tests",
                R.drawable.pythonquiz,
                "https://www.amazon.in/s?k=python+quiz+book"
        ));

        bookList.add(new Book(
                "SQL MCQs Handbook",
                R.drawable.sqlquiz,
                "https://www.amazon.in/s?k=sql+quiz+book"
        ));

        bookList.add(new Book(
                "General Knowledge Quiz Book",
                R.drawable.gkquiz,
                "https://www.amazon.in/s?k=general+knowledge+quiz+book"
        ));
    }
}
