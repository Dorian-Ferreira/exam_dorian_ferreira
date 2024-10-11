package fr.dorian_ferreira.exam.service.interfaces;

import java.security.Principal;

public interface DeleteLoggedServiceInterface<ID> {
    boolean delete(ID id, Principal principal);
}
