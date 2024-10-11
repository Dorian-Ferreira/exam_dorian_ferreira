package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.CoordinateCreateDto;
import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.repository.CoordinateRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoordinateService implements
        CreateServiceInterface<Coordinate, CoordinateCreateDto>
{
    private final CoordinateRepository coordinateRepository;

    @Override
    public Coordinate create(CoordinateCreateDto coordinateCreateDto) {
        Coordinate coordinate = new Coordinate();

        coordinate.setLatitude(coordinateCreateDto.getLatitude());
        coordinate.setLongitude(coordinateCreateDto.getLongitude());

        return coordinateRepository.saveAndFlush(coordinate);
    }
}
