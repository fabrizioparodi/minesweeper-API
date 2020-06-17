package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.ClickRequest;
import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.GameRequest;

import java.util.List;

public interface GameService {

    List<Game> findGamesByUser(String username);

    Game createGame(GameRequest request);

    Game findGame(String id);

    Game pauseGame(String id);

    Game discoverCell(ClickRequest request);

    Game flagCell(ClickRequest request);
}
