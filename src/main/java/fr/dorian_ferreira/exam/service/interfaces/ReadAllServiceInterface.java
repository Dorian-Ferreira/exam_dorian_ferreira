package fr.dorian_ferreira.exam.service.interfaces;


import java.util.List;

public interface ReadAllServiceInterface<TYPE> {
    List<TYPE> findAll();
}
