package com.example.models;

import lombok.Data;

@Data
public class ScoreCard {
    String playerName;
    int score;
    int noOf4s;
    int noOf6s;
    int balls;

    public ScoreCard(String playerName, int score, int noOf4s, int noOf6s, int balls) {
        this.playerName = playerName;
        this.score = score;
        this.noOf4s = noOf4s;
        this.noOf6s = noOf6s;
        this.balls = balls;
    }
}
