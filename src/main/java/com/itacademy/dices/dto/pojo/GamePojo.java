package com.itacademy.dices.dto.pojo;

import com.itacademy.dices.dto.Result;


public class GamePojo {

    private Integer gameId;

    private Integer firstDice;

    private Integer secondDice;


    public GamePojo() {
    }

    public GamePojo(Integer gameId, Integer firstDice, Integer secondDice) {
        this.gameId = gameId;
        this.firstDice = firstDice;
        this.secondDice = secondDice;

    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getFirstDice() {
        return firstDice;
    }

    public void setFirstDice(Integer firstDice) {
        this.firstDice = firstDice;
    }

    public Integer getSecondDice() {
        return secondDice;
    }

    public void setSecondDice(Integer secondDice) {
        this.secondDice = secondDice;
    }

}
