package fr.dorian_ferreira.exam.service.interfaces;

import java.security.Principal;

public interface CreateLoggedServiceInterface<TYPE, DTO> {
    TYPE create(DTO dto, Principal principal);
}
