package fr.dorian_ferreira.exam.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.entity.User;
import fr.dorian_ferreira.exam.json_views.JsonViews;
import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @GetMapping(UrlRoute.USER_PROFILE)
    @JsonView(JsonViews.UserShow.class)
    public User show(Principal principal) {
        return userService.findByPrincipal(principal);
    }

    @PostMapping(UrlRoute.USER_AVATAR)
    public ResponseEntity<Boolean> uploadPhoto(
            @RequestParam("userPhoto") MultipartFile file,
            Principal principal
    ) {
        return new ResponseEntity<>(userService.uploadImage(file, principal), HttpStatus.ACCEPTED);
    }
}
