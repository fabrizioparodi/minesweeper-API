package com.deviget.exercise.minesweeper.model;

import lombok.Data;

@Data
public class GameRequest {

    private String username;
    private int rows;
    private int cols;
    private int mines;
}
