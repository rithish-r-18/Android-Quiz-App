package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    List<Book> bookList;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.bookList = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.ivBookImage.setImageResource(book.getImageRes());

        holder.btnBuy.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getBuyUrl()));
            context.startActivity(intent);
        });

        holder.btnCart.setOnClickListener(v -> {
            // Simulate adding to cart
            Intent cartIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getBuyUrl()));
            context.startActivity(cartIntent);
        });

        holder.btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this Quiz Book!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, book.getTitle() + "\n" + book.getBuyUrl());
            context.startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookTitle;
        ImageView ivBookImage;
        Button btnBuy, btnCart, btnShare;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            ivBookImage = itemView.findViewById(R.id.ivBookImage);
            btnBuy = itemView.findViewById(R.id.btnBuy);
            btnCart = itemView.findViewById(R.id.btnCart);
            btnShare = itemView.findViewById(R.id.btnShare);
        }
    }
}
