package fr.dorian_ferreira.exam.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.dto.RoundCreateDto;
import fr.dorian_ferreira.exam.dto.RoundUpdateDto;
import fr.dorian_ferreira.exam.entity.Round;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.service.RoundService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RoundRestController {

    private RoundService roundService;

    @PostMapping(UrlRoute.BASE_ROUND)
    @JsonView(JsonViews.RoundShow.class)
    public Round create(@Valid @RequestBody RoundCreateDto roundCreateDto) {
        return roundService.create(roundCreateDto);
    }

    @PutMapping(UrlRoute.BASE_ROUND + "/{id}")
    @JsonView(JsonViews.RoundShow.class)
    public Round update(@Valid @RequestBody RoundUpdateDto roundUpdateDto, @PathVariable Long id) {
        return roundService.update(roundUpdateDto, id);
    }

}
