package fr.dorian_ferreira.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameCreateDto {
    private Boolean hasPan;

    private Boolean hasZoom;

    private Boolean hasMove;

    private Integer maximumTime;

    private Integer round;

    private Long mapId;
}
