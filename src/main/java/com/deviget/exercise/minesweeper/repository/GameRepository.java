package com.deviget.exercise.minesweeper.repository;

import com.deviget.exercise.minesweeper.model.Game;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, String> {

    @Query("{'username': ?0}")
    List<Game> findByUser(String username);
}
