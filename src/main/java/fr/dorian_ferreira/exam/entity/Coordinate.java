package fr.dorian_ferreira.exam.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViewsCoordinate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {

    // La Composite est sur une branche EmbeddedId

    @JsonView(JsonViewsCoordinate.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewsCoordinate.Latitude.class)
    @Column(nullable = false)
    private String latitude;

    @JsonView(JsonViewsCoordinate.Longitude.class)
    @Column(nullable = false)
    private String longitude;
}
