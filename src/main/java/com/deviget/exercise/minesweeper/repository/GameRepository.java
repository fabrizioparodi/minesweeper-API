package com.deviget.exercise.minesweeper.repository;

import com.deviget.exercise.minesweeper.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, String> {
}
