package com.itacademy.dices.controller.impl;

import com.itacademy.dices.controller.IGameController;
import com.itacademy.dices.controller.exceptions.HistoryException;
import com.itacademy.dices.controller.exceptions.InvalidJSON;
import com.itacademy.dices.controller.exceptions.UserNotFound;
import com.itacademy.dices.dto.pojo.GamePojo;
import com.itacademy.dices.dto.response.GameResponse;
import com.itacademy.dices.dto.response.UserResponse;
import com.itacademy.dices.repository.GameRepository;
import com.itacademy.dices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2")
public class GameController implements IGameController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/players/{id}/games")
    @Override
    public ResponseEntity<GameResponse> playGame(@PathVariable(value = "id") Integer id, @RequestBody GamePojo game)
                                                                            throws UserNotFound, InvalidJSON {
        if (!Operations.isValidGame(game)) {
            throw new InvalidJSON("Game not valid");
        }
        GameResponse newGame = new GameResponse();
        newGame.setFirstDice(game.getFirstDice());
        newGame.setSecondDice(game.getSecondDice());
        newGame.setGameId(game.getGameId());
        newGame.setResult(Operations.getResult(game));
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFound("User not found");
        }
        UserResponse user = userRepository.findById(id).get();
            newGame.setUser(user);

            gameRepository.save(newGame);

        return new ResponseEntity<>(newGame,HttpStatus.OK);

    }

    @DeleteMapping("/players/{id}")
    @Override
    public ResponseEntity<?> deleteGameHistory(@PathVariable(value = "id") Integer id)
                                                                            throws UserNotFound, HistoryException {
        Optional<UserResponse> userResponse = userRepository.findById(id);
        if (userResponse.isEmpty()) {
            throw new UserNotFound("user with id: " + id + " not found.");
        }
        UserResponse user = userResponse.get();
        if (user.getGameList().isEmpty()) {
            throw new HistoryException();
        }
        gameRepository.findByUserId(id).stream().forEach(game -> {
            gameRepository.delete(game);
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/players/{id}/games")
    @Override
    public ResponseEntity<List<GameResponse>> seeGameHistory(@PathVariable(value = "id") Integer id)
                                                                                        throws UserNotFound {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFound("user with id: " + id + " not found.");
        }
        return new ResponseEntity<>(gameRepository.findByUserId(id), HttpStatus.OK);
    }
}
