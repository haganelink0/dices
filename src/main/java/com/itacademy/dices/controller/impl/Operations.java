package com.itacademy.dices.controller.impl;

import com.itacademy.dices.dto.pojo.UserPojo;
import com.itacademy.dices.dto.response.GameResponse;
import com.itacademy.dices.dto.response.UserResponse;

import java.time.Instant;
import java.time.LocalDate;
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
        if (user.getRegistrationDate() == null || user.getRegistrationDate().before(Date.from(Instant.now()))) {
            answer =false;
        }
        if (user.getWinrate() == null || user.getWinrate() <= 0) {
            answer = false;
        }
        if (user.getGameList() == null) {
            answer = false;
        }
        return answer;
    }
}
