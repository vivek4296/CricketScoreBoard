package com.example;

import com.example.commands.ICommand;
import com.example.enums.InputCommand;
import com.example.models.Command;
import com.example.service.CricketScoreBoard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the cricket ScoredBoard");
        CricketScoreBoard cricketScoreBoard = new CricketScoreBoard();
        while (scanner.hasNextLine()) {
            String commands = scanner.nextLine();
            Command command = new Command(commands);
            ICommand iCommand = InputCommand.valueOf(command.getCommandName()).getCommandClass(cricketScoreBoard);
            iCommand.execute(command);
        }
    }
}
