package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.Game;

public interface GameEngine {

    void initializeCells(Game game);

    void discoverCell(Game game, int posX, int posY);

    void flagCell(Game game, int posX, int posY);

    void togglePause(Game game);

    boolean isOver(Game game);

    boolean isPaused(Game game);

}
