package com.example.service;

import com.example.constants.Constant;
import com.example.models.ScoreCard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CricketScoreBoard {

    HashMap<String, Map<String, ScoreCard>> scoreBoardHashMap = new HashMap<>();
    HashMap<String, Deque<String>> playerAvailableWithTeam = new HashMap<>();
    HashMap<String, Integer> scoreOfTeam = new HashMap<>();
    HashMap<String,Integer> numberOfPlayersInTeam=new HashMap<>();
    HashMap<String,Integer> numberOfOversForTeam=new HashMap<>();

    public void setPlayerInEachTeam(int playerInEachTeam,String firstTeam,String secondTeam) {

        numberOfPlayersInTeam.put(firstTeam,playerInEachTeam);
        numberOfPlayersInTeam.put(secondTeam,playerInEachTeam);
    }

    public void setOvers(int overs,String firstTeam,String secondTeam) {
        numberOfOversForTeam.put(firstTeam,overs);
        numberOfOversForTeam.put(secondTeam,overs);
    }

    public void createBattingOrderOfTeam(String teamName, List<String> battingOrder) {

        scoreBoardHashMap.putIfAbsent(teamName, new HashMap<>());
        playerAvailableWithTeam.putIfAbsent(teamName, new ArrayDeque<>());
        for (String batsmanName : battingOrder) {
            playerAvailableWithTeam.get(teamName).addLast(batsmanName);
            scoreBoardHashMap.get(teamName).put(batsmanName, new ScoreCard(batsmanName, 0, 0, 0, 0));
        }
        scoreOfTeam.put(teamName, 0);
    }

    public void overDetails(String teamName, int overNumber, List<String> oversBalls) {
        Map<String, ScoreCard> playerDetails = scoreBoardHashMap.get(teamName);
        String playerOnStrike = playerAvailableWithTeam.get(teamName).pollFirst();
        String secondPlayer = playerAvailableWithTeam.get(teamName).pollFirst();
        int ball = 0;
        int score = scoreOfTeam.get(teamName);
        for (String balls : oversBalls) {
            if (balls.equals(Constant.WIDE)) {
                score++;
                ScoreCard scoreCard = playerDetails.get(playerOnStrike);
                scoreCard.setBalls(scoreCard.getBalls() + 1);
                playerDetails.put(playerOnStrike, scoreCard);
            } else if (balls.equals(Constant.WICKET)) {
                ball++;
                ScoreCard scoreCard = playerDetails.get(playerOnStrike);
                scoreCard.setBalls(scoreCard.getBalls() + 1);
                playerDetails.put(playerOnStrike, scoreCard);
                playerOnStrike = playerAvailableWithTeam.get(teamName).isEmpty() ? null : playerAvailableWithTeam.get(teamName).pollFirst();
            } else {
                ball++;
                int run = Integer.parseInt(balls);
                if (run == 1 || run == 3) {
                    score += run;
                    ScoreCard scoreCard = playerDetails.get(playerOnStrike);
                    scoreCard.setBalls(scoreCard.getBalls() + 1);
                    scoreCard.setScore(scoreCard.getScore() + run);
                    playerDetails.put(playerOnStrike, scoreCard);
                    String nonStrike = playerOnStrike;
                    playerOnStrike = secondPlayer;
                    secondPlayer = nonStrike;
                } else {
                    score += run;
                    ScoreCard scoreCard = playerDetails.get(playerOnStrike);
                    scoreCard.setBalls(scoreCard.getBalls() + 1);
                    if (run == 4) scoreCard.setNoOf4s(scoreCard.getNoOf4s() + 1);
                    if (run == 6) scoreCard.setNoOf6s(scoreCard.getNoOf6s() + 1);
                    scoreCard.setScore(scoreCard.getScore() + run);
                    playerDetails.put(playerOnStrike, scoreCard);
                }
            }
        }
        if (playerOnStrike != null) {
            playerAvailableWithTeam.get(teamName).addFirst(playerOnStrike);
        }
        if (secondPlayer != null) {
            playerAvailableWithTeam.get(teamName).addFirst(secondPlayer);
        }
        scoreOfTeam.put(teamName, score);
        scoreBoardHashMap.put(teamName, playerDetails);
        double overDetails = (ball == 6) ? overNumber : overNumber - 1 + (double) (ball % 6) / 10;
        printScoreBoard(teamName, overDetails);
    }

    private void printScoreBoard(String teamName, double overDetails) {
        Map<String, ScoreCard> playerDetails = scoreBoardHashMap.get(teamName);
        System.out.println("ScoreBoard of the team: " + teamName);
        System.out.println("PlayerName" + " " + "Score" + "   " + "4s" + "   " + "6s" + "   " + "Balls");
        for (Map.Entry<String, ScoreCard> scores : playerDetails.entrySet()) {
            String playerName = scores.getKey();
            ScoreCard scoreCard = scores.getValue();
            System.out.println(playerName + "           " + scoreCard.getScore() + "     " + scoreCard.getNoOf4s() +
                    "     " + scoreCard.getNoOf6s() + "     " + scoreCard.getBalls());
        }
        int noOfWicketsTaken = numberOfPlayersInTeam.get(teamName) - playerAvailableWithTeam.get(teamName).size();
        System.out.println("Total Score of Team: " + scoreOfTeam.get(teamName));
        System.out.println("Wickets Taken: " + noOfWicketsTaken);
        System.out.println("Overs info: " + overDetails);
        System.out.println("End of the Over for the team: " + teamName);
    }

    public void checkResult(String teamFirst,String teamSecond)
    {
        int scoreOfFirstTeam=scoreOfTeam.get(teamFirst);
        int scoreOfSecondTeam=scoreOfTeam.get(teamSecond);
        if(scoreOfFirstTeam==scoreOfSecondTeam){
            System.out.println("There is tie for the math");
            return;
        }
        String result=scoreOfFirstTeam-scoreOfSecondTeam>0?(teamFirst+" won the match by "+(scoreOfFirstTeam-scoreOfSecondTeam)):
                (teamSecond+" won the match by "+(scoreOfSecondTeam-scoreOfFirstTeam));
        System.out.println(result);
    }

}
