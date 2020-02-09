package com.deviget.exercise.minesweeper.model;

import lombok.Data;

@Data
public class DiscoverRequest {

    private String gameId;
    private int posX;
    private int posY;
}
