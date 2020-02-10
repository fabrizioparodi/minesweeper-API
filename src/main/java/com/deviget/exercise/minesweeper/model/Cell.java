package com.deviget.exercise.minesweeper.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
public class Cell {

    private int posX;
    private int posY;
    private boolean discovered;
    private boolean mine;
    private boolean flagged;
    private long value;
    private int index;

    public Cell(int posX, int posY, int index) {
        this.posX = posX;
        this.posY = posY;
        // Optional. Just to get more readable in the response for test purpose.
        this.index = index;
    }

    public void markAsMine() {
        this.mine = true;
    }

    public void markAsDiscovered() {
        this.discovered = true;
    }

    public boolean isAdjacent(Cell other) {
        return this != other && Math.abs(this.getPosX() - other.getPosX()) <= 1 && Math.abs(this.getPosY() - other.getPosY()) <= 1;
    }

    /**
     * Recursive method to discover the adjacent cells without mines near.
     *
     */
    public void discoverMe(List<Cell> cells) {
        this.markAsDiscovered();
        List<Cell> adjacentCells = cells.stream()
                .filter(cell -> this.isAdjacent(cell) && cell.canBeDiscoveredAutomatically())
                .collect(Collectors.toList());

        if (adjacentCells.size() > 0) {
            adjacentCells.forEach(cell -> cell.discoverMe(cells));
        }
    }

    private boolean canBeDiscoveredAutomatically() {
        return !this.isDiscovered() && !this.isMine() && !this.isFlagged() && this.getValue() == 0;
    }
}
