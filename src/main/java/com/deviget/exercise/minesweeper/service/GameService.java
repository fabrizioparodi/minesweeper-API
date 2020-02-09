package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.GameRequest;
import com.deviget.exercise.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Game createGame(GameRequest request) {
        return this.repository.save(new Game(request));
    }

    public Game findGame(String id) {
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Game not exist"));
    }

}
