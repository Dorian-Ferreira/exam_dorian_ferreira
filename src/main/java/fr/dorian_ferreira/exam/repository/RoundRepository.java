package fr.dorian_ferreira.exam.repository;

import fr.dorian_ferreira.exam.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
}
