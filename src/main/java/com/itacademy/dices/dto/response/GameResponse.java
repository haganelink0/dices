package com.itacademy.dices.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.itacademy.dices.dto.Game;
import com.itacademy.dices.dto.Result;

import javax.persistence.*;

@Entity
@Table(name="games")
public class GameResponse implements Game {

    @Id
    @Column(name="game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column(name = "first_dice")
    private Integer firstDice;

    @Column(name = "secondDice")
    private Integer secondDice;

    @Column(name = "result")
    private Result result;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserResponse user;


    public GameResponse() {
    }

    public GameResponse(Integer gameId, UserResponse user) {
        this.gameId = gameId;
        this.user = user;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        if (this.user != null) {
            this.user.removeGame(this);
            this.user = user;
            this.user.addGame(this);
        }
    }


}
