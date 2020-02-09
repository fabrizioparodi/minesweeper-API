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
        return Math.abs(this.getPosX() - other.getPosX()) <= 1 && Math.abs(this.getPosY() - other.getPosY()) <= 1;
    }
}
