package com.deviget.exercise.minesweeper.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        this.initializeCells();
    }

    private void initializeCells() {
        this.cells = new ArrayList<>();
        for (int x = 0; x < this.rows; x++) {
            for (int y = 0; y < this.cols; y++) {
                this.cells.add(new Cell(x, y));
            }
        }

        // Put the mines randomly inside the cells
        Collections.shuffle(cells);
        this.cells.stream().limit(this.mines).forEach(Cell::markAsMine);
        // Optional sort. Just to get more readable in the response.
        this.cells.sort(Comparator.comparing(Cell::getIndex));
    }

    public void discoverCell(int posX, int posY) {
        Cell selectedCell = this.cells.stream()
                .filter(cell -> cell.getPosX() == posX && cell.getPosY() == posY)
                .findFirst().orElseThrow(() -> new RuntimeException("Cell not found for the given coordinates"));

        // Increase the moves
        this.moves++;

        // Discover the clicked cell and the cells around it (the ones without mine or flagged).
        selectedCell.setDiscovered(true);
        this.cells.stream()
                .filter(cell -> selectedCell.isAdjacent(cell) && !cell.isMine() && !cell.isFlagged())
                .forEach(Cell::markAsDiscovered);
    }

}
