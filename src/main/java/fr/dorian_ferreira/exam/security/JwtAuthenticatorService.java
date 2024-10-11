package fr.dorian_ferreira.exam.security;

import fr.dorian_ferreira.exam.custom_response.JwtResponse;
import fr.dorian_ferreira.exam.dto.user.LoginDTO;
import fr.dorian_ferreira.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtAuthenticatorService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public ResponseEntity<JwtResponse> authenticate(LoginDTO dto) {
        try {
            if(userService.findByEmail(dto.getEmail()).isEnabled()) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
                );

                String token = jwtService.generateToken(dto.getEmail());
                return ResponseEntity.ok(new JwtResponse(token));
            }

        } catch (AuthenticationException ignore) {

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
