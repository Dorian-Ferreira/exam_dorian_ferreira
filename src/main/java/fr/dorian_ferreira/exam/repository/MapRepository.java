package fr.dorian_ferreira.exam.repository;

import fr.dorian_ferreira.exam.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {

    @Query("SELECT m FROM Map m JOIN Game g ON m = g.map GROUP BY m ORDER BY count(g) LIMIT 5")
    List<Map> findMostPlayedMap();
}
