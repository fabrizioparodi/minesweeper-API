package com.deviget.exercise.minesweeper.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Game {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    private String username;
    private StatusEnum status;
    private int moves;
    private int rows;
    private int cols;
    private int mines;
    private List<Cell> cells;

    public Game(GameRequest request) {
        this.status = StatusEnum.OPEN;
        this.username = request.getUsername();
        this.rows = request.getRows();
        this.cols = request.getCols();
        this.mines = request.getMines();
    }

}
