package fr.dorian_ferreira.exam.repository;

import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.entity.embedded.CoordinateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, CoordinateId> {
}
