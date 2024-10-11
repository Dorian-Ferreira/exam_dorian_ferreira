package fr.dorian_ferreira.exam.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadAllPagedServiceInterface<TYPE> {
    Page<TYPE> findAll(Pageable pageable);
}
