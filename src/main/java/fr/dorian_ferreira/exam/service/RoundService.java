package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.RoundCreateDto;
import fr.dorian_ferreira.exam.dto.RoundUpdateDto;
import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.entity.Round;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.RoundRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.UpdateServiceInterface;
import fr.dorian_ferreira.exam.service.utils.RoundCalulator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
@AllArgsConstructor
public class RoundService implements
        CreateServiceInterface<Round, RoundCreateDto>,
        UpdateServiceInterface<Round, RoundUpdateDto, Long>,
        ReadOneByIdServiceInterface<Round, Long>
{
    private final RoundRepository roundRepository;
    private final CoordinateService coordinateService;
    private final GamePagedService gameService;

    private final RoundCalulator roundCalulator;

    @Override
    public Round create(RoundCreateDto roundDto) {
        Round round = new Round();

        round.setCreatedAt(LocalDateTime.now());
        round.setGame(gameService.findOneById(roundDto.getGameId()));

        Random random = new Random();
        List<Coordinate> coordinates = coordinateService.findAll();
        Coordinate coordinate = coordinates.get(random.nextInt(0, (coordinates.size() - 1)));

        round.setOrigin(coordinate);

        return roundRepository.saveAndFlush(round);
    }

    @Override
    public Round update(RoundUpdateDto roundUpdateDto, Long id) {
        Round round = findOneById(id);

        round.setSelected(coordinateService.findOneById(roundUpdateDto.getCoordinateId()));
        round.setTime(roundUpdateDto.getTime());

        NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
        try {
            round.setDistance(Math.round(roundCalulator.meters(
                    nf.parse(round.getOrigin().getId().getLatitude()).doubleValue(),
                    nf.parse(round.getOrigin().getId().getLongitude()).doubleValue(),
                    nf.parse(round.getSelected().getId().getLatitude()).doubleValue(),
                    nf.parse(round.getSelected().getId().getLongitude()).doubleValue()
                    )));
        } catch (ParseException e) {
            return null;
        }

        if(round.getDistance() > 10000000) {
            round.setPoints(0);
        } else {
            round.setPoints(roundCalulator.points(5000, round.getDistance(), 10000000L));
        }

        return roundRepository.saveAndFlush(round);
    }

    @Override
    public Round findOneById(Long id) {
        return roundRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Round"));
    }
}
