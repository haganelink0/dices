package com.itacademy.dices.controller;

import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.response.GameResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGameController {

    void playGame(Integer id, GameResponse game) throws UserNotFound;

    void deleteGameHistory(Integer id);

    ResponseEntity<List<GameResponse>> seeGameHistory(Integer id) throws UserNotFound;
}
