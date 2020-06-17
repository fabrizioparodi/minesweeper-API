package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.GameFixture;
import com.deviget.exercise.minesweeper.model.Cell;
import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.StatusEnum;
import org.junit.Assert;
import org.junit.Test;

public class GameEngineTest {
    private final GameEngine gameEngine;

    public GameEngineTest() {
        this.gameEngine = new GameEngineImpl();
    }

    @Test
    public void testDiscoverCell_WhenHasGivenCoordinates_ThenShouldSucceed() {
        try {
            Game game = GameFixture.buildGame();
            this.gameEngine.initializeCells(game);
            this.gameEngine.discoverCell(game, 1, 1);

            Cell cell = game.findCell(1, 1);
            Assert.assertTrue(cell.isDiscovered());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDiscoverCell_WhenHasNotGivenCoordinates_ThenShouldFail() {
        try {
            Game game = GameFixture.buildGame();
            this.gameEngine.initializeCells(game);
            this.gameEngine.discoverCell(game, 99, 99);
            Assert.fail("Test should failed");
        } catch (Exception e) {
            Assert.assertEquals("Cell not found for the given coordinates", e.getMessage());
        }
    }

    @Test
    public void testDiscoverCell_WhenGameIsPaused_ThenShouldFail() {
        try {
            Game game = GameFixture.buildGame();
            this.gameEngine.togglePause(game);
            this.gameEngine.discoverCell(game, 1, 1);

            Assert.fail("Test should failed");
        } catch (Exception e) {
            Assert.assertEquals("Game is paused, cannot update it", e.getMessage());
        }
    }

    @Test
    public void testDiscoverCell_WhenGameIsOver_ThenShouldFail() {
        try {
            Game game = GameFixture.buildGame();
            game.setStatus(StatusEnum.LOST);
            this.gameEngine.initializeCells(game);
            this.gameEngine.discoverCell(game, 1, 1);

            Assert.fail("Test should failed");
        } catch (Exception e) {
            Assert.assertEquals("Game already finished, cannot update it", e.getMessage());
        }
    }

    @Test
    public void testFlagCell_WhenHasGivenCoordinates_ThenShouldSucceed() {
        try {
            Game game = GameFixture.buildGame();
            this.gameEngine.initializeCells(game);
            this.gameEngine.flagCell(game, 1, 1);

            Cell cell = game.findCell(1, 1);
            Assert.assertTrue(cell.isFlagged());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
