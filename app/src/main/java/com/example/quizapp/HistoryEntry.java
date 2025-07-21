package com.example.quizapp;

public class HistoryEntry {
    private String topic;
    private int score;
    private int total;
    private String time;

    public HistoryEntry(String topic, int score, int total, String time) {
        this.topic = topic;
        this.score = score;
        this.total = total;
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public int getScore() {
        return score;
    }

    public int getTotal() {
        return total;
    }

    public String getTime() {      // ✅ Add this method
        return time;
    }
}
