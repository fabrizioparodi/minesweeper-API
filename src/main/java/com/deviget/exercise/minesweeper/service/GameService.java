package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.DiscoverRequest;
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

    public Game discoverCell(DiscoverRequest request) {
        Game game = findGame(request.getGameId());
        game.discoverCell(request.getPosX(), request.getPosY());
        return this.repository.save(game);
    }

}
