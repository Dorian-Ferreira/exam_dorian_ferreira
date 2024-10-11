package fr.dorian_ferreira.exam.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.dto.CoordinateCreateDto;
import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.service.CoordinateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CoordinateRestController {

    private CoordinateService coordinateService;

    @PostMapping(UrlRoute.BASE_COORDINATE)
    @JsonView(JsonViews.CoordinateShow.class)
    public Coordinate create(@Valid @RequestBody CoordinateCreateDto coordinateCreateDto) {
        return coordinateService.create(coordinateCreateDto);
    }

}
