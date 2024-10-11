package fr.dorian_ferreira.exam.service.interfaces;

public interface ReadOneByIdServiceInterface<TYPE, ID> {
    TYPE findOneById(ID id);
}
