package fr.dorian_ferreira.exam.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.custom_response.CustomPageResponse;
import fr.dorian_ferreira.exam.dto.GameCreateDto;
import fr.dorian_ferreira.exam.entity.Game;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class GameRestController {

    private GameService gameService;

    @GetMapping(UrlRoute.BASE_GAME)
    @JsonView(JsonViews.GameList.class)
    public CustomPageResponse list(@PageableDefault(
            size = 12
    ) Pageable pageable) {
        Page<Game> games = gameService.findAll(pageable);

        CustomPageResponse customPageResponse = new CustomPageResponse();

        customPageResponse.setStatus(HttpStatus.OK.value());
        customPageResponse.setEntity("Game");

        customPageResponse.setPageQuantity(games.getNumberOfElements());
        customPageResponse.setTotalQuantity(games.getTotalElements());
        customPageResponse.setPageNumber(games.getNumber());
        customPageResponse.setTotalPageNumber(games.getTotalPages());

        customPageResponse.setData(games.getContent());

        return customPageResponse;
    }

    @GetMapping(UrlRoute.BASE_GAME + "/{id}")
    @JsonView(JsonViews.GameShow.class)
    public Game show(@PathVariable String id) {
        return gameService.findOneById(id);
    }

    @GetMapping(UrlRoute.GAME_SCORES)
    @JsonView(JsonViews.GameList.class)
    public List<Game> scores() {
        return gameService.findBestScores();
    }

    @GetMapping(UrlRoute.GAME_LAST)
    @JsonView(JsonViews.GameList.class)
    public List<Game> last() {
        return gameService.findLastPlayed();
    }

    @PostMapping(UrlRoute.BASE_GAME)
    @JsonView(JsonViews.GameShow.class)
    public Game create(@Valid @RequestBody GameCreateDto gameDto, Principal principal) {
        return gameService.create(gameDto, principal);
    }
}
