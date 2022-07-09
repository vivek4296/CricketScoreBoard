package com.example.commands;

import com.example.models.Command;
import com.example.service.CricketScoreBoard;

public class ResultService extends ICommand{
    public ResultService(CricketScoreBoard cricketScoreBoard) {
        super(cricketScoreBoard);
    }

    @Override
    public void execute(Command command) {
        cricketScoreBoard.checkResult(command.getParams().get(0),command.getParams().get(1));
    }
}
