package com.itacademy.dices.controller.impl;

import com.itacademy.dices.controller.IGameController;
import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.response.GameResponse;
import com.itacademy.dices.repository.GameRepository;
import com.itacademy.dices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController implements IGameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/{id}/games")
    @Override
    public void playGame(@PathVariable(value = "id") Integer id, @RequestBody GameResponse game)
                                                                                        throws UserNotFound {
        userRepository.findById(id).map( user -> {
            game.setUser(user);
            return gameRepository.save(game);
        }).orElseThrow(()->new UserNotFound("user not found"));

    }

    @DeleteMapping("/users/{id}")
    @Override
    public void deleteGameHistory(@PathVariable(value = "id") Integer id) {
        List<GameResponse> games = gameRepository.findByUserId(id);
        games.stream().forEach((game) ->{
            gameRepository.delete(game);
        });

    }

    @GetMapping("/users/{id}/games")
    @Override
    public ResponseEntity<List<GameResponse>> seeGameHistory(@PathVariable(value = "id") Integer id)
                                                                                        throws UserNotFound {
        return new ResponseEntity<>(gameRepository.findByUserId(id), HttpStatus.OK);
    }
}
