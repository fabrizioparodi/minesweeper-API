package com.deviget.exercise.minesweeper.controller;

import com.deviget.exercise.minesweeper.model.Game;
import com.deviget.exercise.minesweeper.model.GameRequest;
import com.deviget.exercise.minesweeper.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api")
@Api(value = "Minesweeper Game API")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("new")
    @ApiOperation(value = "Return a new game", response = Game.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Game created successfully")})
    public ResponseEntity<?> createGame(@RequestBody GameRequest request) {
        try {
            return ResponseEntity.ok(this.service.createGame(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("load/{id}")
    @ApiOperation(value = "Load a specified game", response = Game.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Game loaded successfully")})
    public ResponseEntity<?> loadGame(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.service.findGame(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}