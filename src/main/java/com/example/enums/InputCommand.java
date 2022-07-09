package com.example.enums;

import com.example.commands.BattingOrderService;
import com.example.commands.ICommand;
import com.example.commands.MatchInfoService;
import com.example.commands.OverService;
import com.example.commands.ResultService;
import com.example.service.CricketScoreBoard;

public enum InputCommand {
    MATCHINFO() {
        @Override
        public ICommand getCommandClass(CricketScoreBoard cricketScoreBoard) {
            return new MatchInfoService(cricketScoreBoard);
        }
    },
    BATTINGORDER() {
        @Override
        public ICommand getCommandClass(CricketScoreBoard cricketScoreBoard) {
            return new BattingOrderService(cricketScoreBoard);
        }
    },
    OVER() {
        @Override
        public ICommand getCommandClass(CricketScoreBoard cricketScoreBoard) {
            return new OverService(cricketScoreBoard);
        }
    },
    RESULT(){
        @Override
        public ICommand getCommandClass(CricketScoreBoard cricketScoreBoard) {
            return new ResultService(cricketScoreBoard);
        }
    };

    public abstract ICommand getCommandClass(CricketScoreBoard cricketScoreBoard);
}
