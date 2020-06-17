package com.deviget.exercise.minesweeper;

import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.GameRequest;

public class GameFixture {

    public static Game buildGame() {
        GameRequest gameRequest = new GameRequest();
        gameRequest.setUsername("TEST_USER");
        gameRequest.setCols(5);
        gameRequest.setRows(5);
        gameRequest.setMines(3);

        return new Game(gameRequest);
    }
}
