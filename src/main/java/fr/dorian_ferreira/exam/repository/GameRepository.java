package fr.dorian_ferreira.exam.repository;

import fr.dorian_ferreira.exam.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
}
