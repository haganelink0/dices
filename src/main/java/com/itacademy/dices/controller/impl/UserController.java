package com.itacademy.dices.controller.impl;

import com.itacademy.dices.controller.IUserController;
import com.itacademy.dices.controller.exceptions.InvalidJSON;
import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.pojo.UserPojo;
import com.itacademy.dices.dto.response.UserResponse;
import com.itacademy.dices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController implements IUserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "id") Integer id) throws UserNotFound{
        Optional<UserResponse> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFound("User not found");
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @PostMapping("/users")
    @Override
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserPojo user) throws InvalidJSON {
        if (!Operations.isValid(user)) {
            throw new InvalidJSON();
        }
        UserResponse userDTO = new UserResponse();
        if (Operations.originalName(userRepository.findAll(), user.getName())) {
            userDTO.setName(user.getName());
            userDTO.setId(user.getId());
            userDTO.setRegistrationDate(user.getRegistrationDate());
            userDTO.setGameList(user.getGameList());
            userDTO.setWinrate(user.getWinrate());
            userRepository.save(userDTO);
        } else {
            throw new InvalidJSON("User name is already picked");
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    @Override
    public ResponseEntity<UserResponse> changeName(@PathVariable(value = "id") Integer id,
                                                   @RequestParam String newName)
                                                                                throws UserNotFound {
        Optional<UserResponse> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFound("user not found");
        }
        user.get().setName(newName);
        userRepository.save(user.get());

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    @GetMapping("/users")
    @Override
    public ResponseEntity<List<UserResponse>> seeAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public Double getAverageWinrate() {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> getWorsePlayer() {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> getBestPlayer() {
        return null;
    }
}
