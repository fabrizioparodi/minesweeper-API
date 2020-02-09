package com.deviget.exercise.minesweeper.controller;

import com.deviget.exercise.minesweeper.model.ClickRequest;
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("load/user/{username}")
    @ApiOperation(value = "Get all the games for the specified user", response = Game.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Games retrieved successfully")})
    public ResponseEntity<?> getGamesByUser(@PathVariable String username) {
        try {
            return ResponseEntity.ok(this.service.findGamesByUser(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("discover")
    @ApiOperation(value = "Discover a cell for the specified game", response = Game.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Game updated successfully")})
    public ResponseEntity<?> discoverCell(@RequestBody ClickRequest request) {
        try {
            return ResponseEntity.ok(this.service.discoverCell(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("flag")
    @ApiOperation(value = "Flag a cell for the specified game", response = Game.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Game updated successfully")})
    public ResponseEntity<?> flagCell(@RequestBody ClickRequest request) {
        try {
            return ResponseEntity.ok(this.service.flagCell(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("pause/{id}")
    @ApiOperation(value = "Pause or unpause a game", response = Game.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Game updated successfully")})
    public ResponseEntity<?> pauseGame(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.service.pauseGame(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
