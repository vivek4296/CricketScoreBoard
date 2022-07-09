package com.example.commands;

import com.example.models.Command;
import com.example.service.CricketScoreBoard;

public class OverService extends ICommand {
    public OverService(CricketScoreBoard cricketScoreBoard) {
        super(cricketScoreBoard);
    }

    @Override
    public void execute(Command command) {
        String teamName = command.getParams().get(0);
        command.getParams().remove(0);
        int overNumber = Integer.parseInt(command.getParams().get(0));
        command.getParams().remove(0);
        cricketScoreBoard.overDetails(teamName, overNumber, command.getParams());
    }
}
