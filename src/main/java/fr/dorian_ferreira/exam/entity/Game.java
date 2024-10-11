package fr.dorian_ferreira.exam.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViewsGame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @JsonView(JsonViewsGame.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonView(JsonViewsGame.MaximumTime.class)
    @Column(nullable = false)
    private Integer maximumTime;

    @JsonView(JsonViewsGame.HasMove.class)
    @Column(nullable = false)
    private Boolean hasMove;

    @JsonView(JsonViewsGame.HasPan.class)
    @Column(nullable = false)
    private Boolean hasPan;

    @JsonView(JsonViewsGame.HasZoom.class)
    @Column(nullable = false)
    private Boolean hasZoom;

    @JsonView(JsonViewsGame.CreatedAt.class)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsGame.NbRounds.class)
    @Column(nullable = false)
    private Integer nbRounds;

    @JsonView(JsonViewsGame.User.class)
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @JsonView(JsonViewsGame.Map.class)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Map map;

    @JsonView(JsonViewsGame.Rounds.class)
    @OneToMany(mappedBy = "game")
    private List<Round> rounds = new ArrayList<>();

    @JsonView(JsonViewsGame.TotalPoints.class)
    public Integer getTotalPoints() {
        return rounds.stream().mapToInt(Round::getPoints).sum();
    }
}
