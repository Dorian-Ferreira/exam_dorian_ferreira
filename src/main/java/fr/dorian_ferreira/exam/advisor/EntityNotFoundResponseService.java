package fr.dorian_ferreira.exam.advisor;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.custom_response.CustomErrorResponse;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundResponseService {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @JsonView(JsonViews.CustomResponse.class)
    public CustomErrorResponse entityNotFound(EntityNotFoundException exception) {
        CustomErrorResponse customResponse = new CustomErrorResponse();

        customResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customResponse.setEntity(exception.getEntity());
        customResponse.setData("Entity not found");

        return customResponse;
    }
}
