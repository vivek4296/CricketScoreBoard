package com.example.commands;

import com.example.models.Command;
import com.example.service.CricketScoreBoard;

public class MatchInfoService extends ICommand {


    public MatchInfoService(CricketScoreBoard cricketScoreBoard) {
        super(cricketScoreBoard);
    }

    @Override
    public void execute(Command command) {
        cricketScoreBoard.setPlayerInEachTeam(Integer.parseInt(command.getParams().get(0)),command.getParams().get(2),command.getParams().get(3));
        cricketScoreBoard.setOvers(Integer.parseInt(command.getParams().get(1)),command.getParams().get(2),command.getParams().get(3));

    }
}
