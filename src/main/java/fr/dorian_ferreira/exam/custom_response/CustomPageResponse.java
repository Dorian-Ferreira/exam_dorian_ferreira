package fr.dorian_ferreira.exam.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomPageResponse extends CustomResponse {
    @JsonView(JsonViews.CustomPageResponse.class)
    private long totalQuantity;

    @JsonView(JsonViews.CustomPageResponse.class)
    private long pageQuantity;

    @JsonView(JsonViews.CustomPageResponse.class)
    private long pageNumber;

    @JsonView(JsonViews.CustomPageResponse.class)
    private long totalPageNumber;
}
