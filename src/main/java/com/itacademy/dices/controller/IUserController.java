package com.itacademy.dices.controller;

import com.itacademy.dices.controller.exceptions.InvalidJSON;
import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.pojo.UserPojo;
import com.itacademy.dices.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {

    ResponseEntity<UserResponse> saveUser(UserPojo user) throws InvalidJSON;

    ResponseEntity<UserResponse> changeName(Integer id, String newName) throws UserNotFound;

    ResponseEntity<List<UserResponse>> seeAllUsers();

    Double getAverageWinrate();

    ResponseEntity<UserResponse> getWorsePlayer();

    ResponseEntity<UserResponse> getBestPlayer();
}
