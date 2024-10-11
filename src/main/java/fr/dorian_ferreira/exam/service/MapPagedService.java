package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.MapCreateDto;
import fr.dorian_ferreira.exam.entity.Map;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.MapRepository;
import fr.dorian_ferreira.exam.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MapPagedService implements
        CreateServiceInterface<Map, MapCreateDto>,
        ReadOneByIdServiceInterface<Map, Long>,
        ReadAllPagedServiceInterface<Map>,
        UpdateServiceInterface<Map, MapCreateDto, Long>
{
    private final MapRepository mapRepository;

    @Override
    public Map create(MapCreateDto mapCreateDto) {
        Map map = new Map();

        map.setCreatedAt(LocalDateTime.now());
        map.setName(mapCreateDto.getName());

        return mapRepository.saveAndFlush(map);
    }

    @Override
    public Map update(MapCreateDto mapCreateDto, Long id) {
        Map map = findOneById(id);

        map.setName(mapCreateDto.getName());

        return mapRepository.saveAndFlush(map);
    }

    @Override
    public Map findOneById(Long id) {
        return mapRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Map"));
    }

    @Override
    public Page<Map> findAll(Pageable pageable) {
        return mapRepository.findAll(pageable);
    }

    public List<Map> findMostPlayedMap() {
        return mapRepository.findMostPlayedMap();
    }
}
