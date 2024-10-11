package fr.dorian_ferreira.exam.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.custom_response.CustomPageResponse;
import fr.dorian_ferreira.exam.dto.MapCreateDto;
import fr.dorian_ferreira.exam.entity.Map;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.service.MapService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MapRestController {

    private MapService mapService;

    @GetMapping(UrlRoute.BASE_MAP)
    @JsonView(JsonViews.MapList.class)
    public CustomPageResponse list(@PageableDefault(
            size = 12
    ) Pageable pageable) {
        Page<Map> maps = mapService.findAll(pageable);

        CustomPageResponse customPageResponse = new CustomPageResponse();

        customPageResponse.setStatus(HttpStatus.OK.value());
        customPageResponse.setEntity("Map");

        customPageResponse.setPageQuantity(maps.getNumberOfElements());
        customPageResponse.setTotalQuantity(maps.getTotalElements());
        customPageResponse.setPageNumber(maps.getNumber());
        customPageResponse.setTotalPageNumber(maps.getTotalPages());

        customPageResponse.setData(maps.getContent());

        return customPageResponse;
    }

    @GetMapping(UrlRoute.BASE_MAP + "/{id}")
    @JsonView(JsonViews.MapShow.class)
    public Map show(@PathVariable Long id) {
        return mapService.findOneById(id);
    }

    @GetMapping(UrlRoute.MAP_BEST)
    @JsonView(JsonViews.MapList.class)
    public List<Map> best() {
        return mapService.findMostPlayedMap();
    }

    @PostMapping(UrlRoute.BASE_MAP)
    @JsonView(JsonViews.MapShow.class)
    public Map create(@Valid @RequestBody MapCreateDto mapCreateDto) {
        return mapService.create(mapCreateDto);
    }

    @PutMapping(UrlRoute.BASE_MAP + "/{id}")
    @JsonView(JsonViews.MapShow.class)
    public Map update(@Valid @RequestBody MapCreateDto mapCreateDto, @PathVariable Long id) {
        return mapService.update(mapCreateDto, id);
    }
}
