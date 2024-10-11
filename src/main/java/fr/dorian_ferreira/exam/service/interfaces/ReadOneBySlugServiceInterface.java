package fr.dorian_ferreira.exam.service.interfaces;

public interface ReadOneBySlugServiceInterface<TYPE> {
    TYPE findOneBySlug(String slug);
}
