package com.example.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;  // ✅ Declare context
    private List<HistoryEntry> historyList;

    // ✅ Constructor to receive context
    public HistoryAdapter(Context context, List<HistoryEntry> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // ✅ Use context to inflate the layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryEntry entry = historyList.get(position);
        holder.tvTopic.setText("Topic: " + entry.getTopic());
        holder.tvScore.setText("Score: " + entry.getScore() + "/" + entry.getTotal());
        holder.tvTime.setText(entry.getTime());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopic, tvScore, tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTopic = itemView.findViewById(R.id.tvHistoryTopic);
            tvScore = itemView.findViewById(R.id.tvHistoryScore);
            tvTime = itemView.findViewById(R.id.tvHistoryTime);
        }
    }
}
