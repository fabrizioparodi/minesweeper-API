package com.deviget.exercise.minesweeper.service;

import com.deviget.exercise.minesweeper.model.Cell;
import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class GameEngineImpl implements GameEngine {

    public void initializeCells(Game game) {
        List<Cell> cells = new ArrayList<>();
        for (int x = 0; x < game.getRows(); x++) {
            for (int y = 0; y < game.getCols(); y++) {
                cells.add(new Cell(x, y, cells.size()));
            }
        }

        // Put the mines randomly inside the cells
        Collections.shuffle(cells);
        cells.stream().limit(game.getMines()).forEach(Cell::markAsMine);
        // Optional sort. Just to get more readable in the response for test purpose.
        cells.sort(Comparator.comparing(Cell::getIndex));

        // Sets value for each cell (how many mines has near)
        cells.stream()
                .filter(cell -> !cell.isMine())
                .forEach(cell -> cell.setValue(
                        cells.stream().filter(other -> cell.isAdjacent(other) && other.isMine()).count())
                );

        game.setCells(cells);
    }

    public void discoverCell(Game game, int posX, int posY) {
        validateGameStatus(game);
        Cell selectedCell = game.findCell(posX, posY);

        // If the clicked cell is flagged, there is no action.
        if (selectedCell.isFlagged()) {
            return;
        }

        // Increase the moves
        game.increaseMovement();

        // If the clicked cell is a mine, you lost!
        if (selectedCell.isMine()) {
            game.setStatus(StatusEnum.LOST);
            return;
        }

        // Discover the clicked cell and the cells around it (the ones without mine or flagged and value 0).
        selectedCell.discoverMe(game.getCells());

        // Checks if you win the game.
        if (game.hasAllCellsDiscovered()) {
            game.setStatus(StatusEnum.WON);
        }
    }

    public void flagCell(Game game, int posX, int posY) {
        validateGameStatus(game);
        Cell selectedCell = game.findCell(posX, posY);

        // Flag the clicked cell.
        selectedCell.setFlagged(true);
    }

    public void togglePause(Game game) {
        validateGameIsNotOver(game);
        game.setStatus(this.isPaused(game) ? StatusEnum.OPEN : StatusEnum.PAUSED);
    }

    public boolean isOver(Game game) {
        return StatusEnum.WON.equals(game.getStatus()) || StatusEnum.LOST.equals(game.getStatus());
    }

    public boolean isPaused(Game game) {
        return StatusEnum.PAUSED.equals(game.getStatus());
    }

    private void validateGameStatus(Game game) {
        validateGameIsNotPaused(game);
        validateGameIsNotOver(game);
    }

    private void validateGameIsNotPaused(Game game) {
        if (this.isPaused(game)) {
            throw new RuntimeException("Game is paused, cannot update it");
        }
    }

    private void validateGameIsNotOver(Game game) {
        if (this.isOver(game)) {
            throw new RuntimeException("Game already finished, cannot update it");
        }
    }

}
