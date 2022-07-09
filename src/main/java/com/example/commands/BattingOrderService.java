package com.example.commands;

import com.example.models.Command;
import com.example.service.CricketScoreBoard;

public class BattingOrderService extends ICommand {
    public BattingOrderService(CricketScoreBoard cricketScoreBoard) {
        super(cricketScoreBoard);
    }

    @Override
    public void execute(Command command) {
        String teamName = command.getParams().get(0);
        command.getParams().remove(0);
        cricketScoreBoard.createBattingOrderOfTeam(teamName, command.getParams());
    }
}
