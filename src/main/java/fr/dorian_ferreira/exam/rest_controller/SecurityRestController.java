package fr.dorian_ferreira.exam.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.custom_response.CustomResponse;
import fr.dorian_ferreira.exam.custom_response.JwtResponse;
import fr.dorian_ferreira.exam.dto.user.UserCreateDTO;
import fr.dorian_ferreira.exam.dto.user.LoginDTO;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.security.JwtAuthenticatorService;
import fr.dorian_ferreira.exam.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private UserService userService;
    private JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping(UrlRoute.LOGIN)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDTO dto) {
        return jwtAuthenticatorService.authenticate(dto);
    }

    @JsonView(JsonViews.UserShow.class)
    @PostMapping(UrlRoute.REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse register(@Valid @RequestBody UserCreateDTO dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.CREATED.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.create(dto));

        return customResponse;
    }
}
