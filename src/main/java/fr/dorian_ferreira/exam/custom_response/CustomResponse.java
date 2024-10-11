package fr.dorian_ferreira.exam.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResponse {
    @JsonView(JsonViews.CustomResponse.class)
    private int status;

    @JsonView(JsonViews.CustomResponse.class)
    private String entity;

    @JsonView(JsonViews.CustomResponse.class)
    private Object data;
}
