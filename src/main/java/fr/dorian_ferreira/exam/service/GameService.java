package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.GameCreateDto;
import fr.dorian_ferreira.exam.entity.Game;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.GameRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateLoggedServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadAllServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService implements
        CreateLoggedServiceInterface<Game, GameCreateDto>,
        ReadOneByIdServiceInterface<Game, String>,
        ReadAllServiceInterface<Game>
{
    private final GameRepository gameRepository;
    private final MapService mapService;
    private final UserService userService;

    @Override
    public Game create(GameCreateDto gameDto, Principal principal) {
        Game game = new Game();

        game.setCreatedAt(LocalDateTime.now());

        game.setHasPan(gameDto.getHasPan());
        game.setHasMove(gameDto.getHasMove());
        game.setHasZoom(gameDto.getHasZoom());
        game.setMaximumTime(gameDto.getMaximumTime());
        game.setNbRounds(gameDto.getRound());

        game.setMap(mapService.findOneById(gameDto.getMapId()));
        game.setUser(userService.findByPrincipal(principal));

        return gameRepository.saveAndFlush(game);
    }


    @Override
    public Game findOneById(String id) {
        return gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game"));
    }

    @Override
    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    public List<Game> findBestScores() {
        return gameRepository.findBestScores();
    }

    public List<Game> findLastPlayed() {
        return gameRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
