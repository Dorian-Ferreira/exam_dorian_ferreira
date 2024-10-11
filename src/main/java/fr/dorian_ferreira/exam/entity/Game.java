package fr.dorian_ferreira.exam.entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Integer maximumTime;

    @Column(nullable = false)
    private Boolean hasMove;

    @Column(nullable = false)
    private Boolean hasPan;

    @Column(nullable = false)
    private Boolean hasZoom;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer nbRounds;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Map map;

    @OneToMany(mappedBy = "game")
    private List<Round> rounds = new ArrayList<>();

    public Integer getTotalPoints() {
        return 0;
    }
}
