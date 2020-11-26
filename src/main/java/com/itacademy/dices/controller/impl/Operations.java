package com.itacademy.dices.controller.impl;

import com.itacademy.dices.dto.Result;
import com.itacademy.dices.dto.pojo.GamePojo;
import com.itacademy.dices.dto.pojo.UserPojo;
import com.itacademy.dices.dto.response.GameResponse;
import com.itacademy.dices.dto.response.UserResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Operations {

    public static boolean originalName(List<UserResponse> users, String name) {
        boolean test = true;
        if (!name.equalsIgnoreCase("anonymous")) {
            for (UserResponse user: users) {
                if (user.getName().equalsIgnoreCase(name)) {
                    test = false;
                }
            }
        }
        return test;
    }

    public static boolean isValid(UserPojo user) {
        boolean answer = true;
        if (user.getId() <= 0) {
            answer = false;
        }
        if (user.getName() == null) {
            answer = false;
        }
        return answer;
    }

    public static boolean isValidGame(GamePojo game) {
        boolean answer = true;
        if (game.getFirstDice() < 1 || game.getFirstDice() > 6) {
            answer = false;
        }
        if (game.getGameId() < 1 || game.getGameId() == null) {
            answer = false;
        }
        if (game.getSecondDice() < 1 || game.getSecondDice() > 6) {
            answer = false;
        }
        return answer;
    }

    public static Result getResult(GamePojo game) {
        if (game.getFirstDice()+game.getSecondDice() == 7) {
            return Result.WIN;
        } else {
            return Result.LOSE;
        }
    }

    public static double updateWinRate(UserResponse user) {
        List<GameResponse> games = user.getGameList();
        double wins = 0.0;
        double loses = 0.0;
        for (GameResponse game : games) {
            if (game.getResult().equals(Result.WIN)){
                wins += 1.0;
            } else {
                loses += 1.0;
            }
        }
        return (wins*100)/(wins+loses);
    }
}
