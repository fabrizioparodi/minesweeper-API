package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.ClickRequest;
import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.GameRequest;
import com.deviget.exercise.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Game> findGamesByUser(String username) {
        return this.repository.findByUser(username);
    }

    public Game pauseGame(String id) {
        Game game = findGame(id);
        validateGameIsNotOver(game);

        game.togglePause();
        return this.repository.save(game);
    }

    public Game discoverCell(ClickRequest request) {
        Game game = findGame(request.getGameId());
        validateGameStatus(game);

        game.discoverCell(request.getPosX(), request.getPosY());
        return this.repository.save(game);
    }

    public Game flagCell(ClickRequest request) {
        Game game = findGame(request.getGameId());
        validateGameStatus(game);

        game.flagCell(request.getPosX(), request.getPosY());
        return this.repository.save(game);
    }

    private void validateGameStatus(Game game) {
        validateGameIsNotPaused(game);
        validateGameIsNotOver(game);
    }

    private void validateGameIsNotPaused(Game game) {
        if (game.isPaused()) {
            throw new RuntimeException("Game is paused, cannot update it.");
        }
    }

    private void validateGameIsNotOver(Game game) {
        if (game.isOver()) {
            throw new RuntimeException("Game already finished, cannot update it.");
        }
    }

}
