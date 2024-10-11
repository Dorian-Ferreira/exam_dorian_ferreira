package fr.dorian_ferreira.exam.repository;

import fr.dorian_ferreira.exam.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
}
