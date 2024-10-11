package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.RoundCreateDto;
import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.entity.Round;
import fr.dorian_ferreira.exam.repository.CoordinateRepository;
import fr.dorian_ferreira.exam.repository.RoundRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class RoundService implements
        CreateServiceInterface<Round, RoundCreateDto>
{
    private final RoundRepository roundRepository;
    private final CoordinateRepository coordinateRepository;
    private final GameService gameService;

    @Override
    public Round create(RoundCreateDto roundDto) {
        Round round = new Round();

        round.setCreatedAt(LocalDateTime.now());
        round.setGame(gameService.findOneById(roundDto.getGameId()));

        Random random = new Random();
        List<Coordinate> coordinates = coordinateRepository.findAll();
        Coordinate coordinate = coordinates.get(random.nextInt(0, (coordinates.size() - 1)));

        round.setOrigin(coordinate);

        return roundRepository.saveAndFlush(round);
    }
}
