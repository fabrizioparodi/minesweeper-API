package com.deviget.exercise.minesweeper.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cell {

    private int posX;
    private int posY;
    private boolean discovered;
    private boolean mine;
    private boolean flagged;

    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void markAsMine() {
        this.mine = true;
    }

    public void markAsDiscovered() {
        this.discovered = true;
    }

    public int getIndex() {
        return posX + posY;
    }

    public boolean isAdjacent(Cell other) {
        return Math.abs(this.getIndex() - other.getIndex()) <= 1;
    }
}
