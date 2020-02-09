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
}
