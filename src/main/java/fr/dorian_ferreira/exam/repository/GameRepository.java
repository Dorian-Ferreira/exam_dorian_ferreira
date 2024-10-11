package fr.dorian_ferreira.exam.repository;

import fr.dorian_ferreira.exam.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    @Query("SELECT g FROM Game g JOIN Round r on r.game = g GROUP BY g ORDER BY sum(r.points) DESC LIMIT 10")
    List<Game> findBestScores();

    List<Game> findTop10ByOrderByCreatedAtDesc();
}
