package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.ClickRequest;
import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.GameRequest;
import com.deviget.exercise.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository repository;
    private final GameEngineImpl gameEngine;

    public GameServiceImpl(GameRepository repository, GameEngineImpl gameEngine) {
        this.repository = repository;
        this.gameEngine = gameEngine;
    }

    public List<Game> findGamesByUser(String username) {
        return this.repository.findByUser(username);
    }

    public Game createGame(GameRequest request) {
        Game game = new Game(request);
        this.gameEngine.initializeCells(game);
        return this.repository.save(game);
    }

    public Game findGame(String id) {
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Game not exist"));
    }

    public Game pauseGame(String id) {
        Game game = findGame(id);
        this.gameEngine.togglePause(game);
        return this.repository.save(game);
    }

    public Game discoverCell(ClickRequest request) {
        Game game = findGame(request.getGameId());
        this.gameEngine.discoverCell(game, request.getPosX(), request.getPosY());
        return this.repository.save(game);
    }

    public Game flagCell(ClickRequest request) {
        Game game = findGame(request.getGameId());
        this.gameEngine.flagCell(game, request.getPosX(), request.getPosY());
        return this.repository.save(game);
    }

}
