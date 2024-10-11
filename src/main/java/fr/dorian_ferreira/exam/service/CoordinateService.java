package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.CoordinateCreateDto;
import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.entity.embedded.CoordinateId;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.CoordinateRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadAllServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoordinateService implements
        CreateServiceInterface<Coordinate, CoordinateCreateDto>,
        ReadAllServiceInterface<Coordinate>,
        ReadOneByIdServiceInterface<Coordinate, CoordinateId>
{
    private final CoordinateRepository coordinateRepository;

    @Override
    public Coordinate create(CoordinateCreateDto coordinateCreateDto) {
        Coordinate coordinate = new Coordinate();

        CoordinateId coordinateId = new CoordinateId();

        coordinateId.setLatitude(coordinateCreateDto.getLatitude());
        coordinateId.setLongitude(coordinateCreateDto.getLongitude());

        coordinate.setId(coordinateId);

        return coordinateRepository.saveAndFlush(coordinate);
    }

    @Override
    public List<Coordinate> findAll() {
        return coordinateRepository.findAll();
    }

    @Override
    public Coordinate findOneById(CoordinateId id) {
        return coordinateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coordinate"));
    }
}
