package fr.dorian_ferreira.exam.service.interfaces;

public interface CreateServiceInterface<TYPE, DTO> {
    TYPE create(DTO dto);
}
