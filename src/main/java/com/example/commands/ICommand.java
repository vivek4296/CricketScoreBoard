package com.example.commands;

import com.example.models.Command;
import com.example.service.CricketScoreBoard;

public abstract class ICommand {

    protected CricketScoreBoard cricketScoreBoard;

    public ICommand(CricketScoreBoard cricketScoreBoard) {
        this.cricketScoreBoard = cricketScoreBoard;
    }

    public abstract void execute(Command command);

}
