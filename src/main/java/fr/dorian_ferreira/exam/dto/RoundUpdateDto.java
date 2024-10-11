package fr.dorian_ferreira.exam.dto;

import fr.dorian_ferreira.exam.entity.embedded.CoordinateId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoundUpdateDto {
    private Integer time;

    private CoordinateId coordinateId;
}
