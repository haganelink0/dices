package com.itacademy.dices.controller.impl;

import com.itacademy.dices.controller.IUserController;
import com.itacademy.dices.controller.exceptions.InvalidJSON;
import com.itacademy.dices.controller.exceptions.InvalidName;
import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.pojo.UserPojo;
import com.itacademy.dices.dto.response.GameResponse;
import com.itacademy.dices.dto.response.UserResponse;
import com.itacademy.dices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController implements IUserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/players/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "id") Integer id) throws UserNotFound{
        Optional<UserResponse> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFound("User not found");
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @PostMapping("/players")
    @Override
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserPojo user) throws InvalidName, InvalidJSON {
        if (!Operations.isValid(user)) {
            throw new InvalidJSON();
        }
        UserResponse userDTO = new UserResponse();
        if (Operations.originalName(userRepository.findAll(), user.getName())) {
            userDTO.setName(user.getName());
            userDTO.setId(user.getId());
            userDTO.setRegistrationDate(Date.from(Instant.now()));
            userDTO.setGameList(new ArrayList<GameResponse>());
            userDTO.setWinrate(0.0);
            userRepository.save(userDTO);
        } else {
            throw new InvalidName();
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("players/{id}")
    @Override
    public ResponseEntity<UserResponse> changeName(@PathVariable(value = "id") Integer id,
                                                   @RequestParam(value = "newName") String newName)
                                                                        throws UserNotFound, InvalidName {
        Optional<UserResponse> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFound("user not found");
        }
        if (!Operations.originalName(userRepository.findAll(), newName)) {
            throw new InvalidName();
        }
        UserResponse updatedUser = new UserResponse();
        updatedUser.setId(user.get().getId());
        updatedUser.setName(newName);
        updatedUser.setGameList(user.get().getGameList());
        updatedUser.setWinrate(user.get().getWinrate());
        updatedUser.setRegistrationDate(user.get().getRegistrationDate());

        userRepository.save(updatedUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @GetMapping("/players")
    @Override
    public ResponseEntity<List<UserResponse>> seeAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/players/ranking")
    @Override
    public Double getAverageWinrate() {
        Double result = 0.0;
        List<UserResponse> players = userRepository.findAll();
        for (UserResponse player : players) {
            result += player.getWinrate();
        }
        return result/players.size();
    }

    @GetMapping("/players/ranking/loser")
    @Override
    public ResponseEntity<UserResponse> getWorsePlayer() {
        UserResponse user = new UserResponse();
        user.setWinrate(100.0);
        List<UserResponse> players = userRepository.findAll();
        for (UserResponse player : players) {
            if (player.getWinrate() < user.getWinrate()) {
                user.setWinrate(player.getWinrate());
                user.setGameList(player.getGameList());
                user.setRegistrationDate(player.getRegistrationDate());
                user.setName(player.getName());
                user.setId(player.getId());
            }
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/players/ranking/winner")
    @Override
    public ResponseEntity<UserResponse> getBestPlayer() {
        UserResponse user = new UserResponse();
        user.setWinrate(0.0);
        List<UserResponse> players = userRepository.findAll();
        for (UserResponse player : players) {
            if (player.getWinrate() > user.getWinrate()) {
                user.setWinrate(player.getWinrate());
                user.setGameList(player.getGameList());
                user.setRegistrationDate(player.getRegistrationDate());
                user.setName(player.getName());
                user.setId(player.getId());
            }
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
