package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.CoordinateCreateDto;
import fr.dorian_ferreira.exam.entity.Coordinate;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.CoordinateRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadAllServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoordinateService implements
        CreateServiceInterface<Coordinate, CoordinateCreateDto>,
        ReadAllServiceInterface<Coordinate>,
        ReadOneByIdServiceInterface<Coordinate, Long>
{
    private final CoordinateRepository coordinateRepository;

    @Override
    public Coordinate create(CoordinateCreateDto coordinateCreateDto) {
        Coordinate coordinate = new Coordinate();

        coordinate.setLatitude(coordinateCreateDto.getLatitude());
        coordinate.setLongitude(coordinateCreateDto.getLongitude());

        return coordinateRepository.saveAndFlush(coordinate);
    }

    @Override
    public List<Coordinate> findAll() {
        return coordinateRepository.findAll();
    }

    @Override
    public Coordinate findOneById(Long id) {
        return coordinateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coordinate"));
    }
}
