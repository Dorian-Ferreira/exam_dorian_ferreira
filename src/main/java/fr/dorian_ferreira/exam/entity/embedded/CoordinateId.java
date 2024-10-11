package fr.dorian_ferreira.exam.entity.embedded;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViewsCoordinate;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateId implements Serializable {
    @JsonView(JsonViewsCoordinate.Id.class)
    @Column(nullable = false)
    private String latitude;

    @JsonView(JsonViewsCoordinate.Id.class)
    @Column(nullable = false)
    private String longitude;
}
