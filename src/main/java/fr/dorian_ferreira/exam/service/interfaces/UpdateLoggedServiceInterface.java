package fr.dorian_ferreira.exam.service.interfaces;

import java.security.Principal;

public interface UpdateLoggedServiceInterface<TYPE, DTO, ID> {
    TYPE update(DTO dto, ID id, Principal principal);
}
