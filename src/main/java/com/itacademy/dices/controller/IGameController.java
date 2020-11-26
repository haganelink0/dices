package com.itacademy.dices.controller;

import com.itacademy.dices.controller.exceptions.HistoryException;
import com.itacademy.dices.controller.exceptions.InvalidJSON;
import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.pojo.GamePojo;
import com.itacademy.dices.dto.response.GameResponse;
import com.itacademy.dices.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGameController {

    ResponseEntity<GameResponse> playGame(Integer id, GamePojo game) throws UserNotFound, InvalidJSON;

    ResponseEntity<?> deleteGameHistory(Integer id) throws UserNotFound, HistoryException;

    ResponseEntity<List<GameResponse>> seeGameHistory(Integer id) throws UserNotFound;
}
