package fr.dorian_ferreira.exam.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViewsRounds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @JsonView(JsonViewsRounds.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewsRounds.Points.class)
    private Integer points;

    @JsonView(JsonViewsRounds.Time.class)
    private Integer time;

    @JsonView(JsonViewsRounds.Distance.class)
    private Long distance;

    @JsonView(JsonViewsRounds.CreatedAt.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Game game;

    @ManyToOne
    private Coordinate selected;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Coordinate origin;
}
