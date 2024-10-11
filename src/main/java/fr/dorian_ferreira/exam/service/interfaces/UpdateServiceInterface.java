package fr.dorian_ferreira.exam.service.interfaces;

public interface UpdateServiceInterface<TYPE, DTO, ID> {
    TYPE update(DTO dto, ID id);
}
