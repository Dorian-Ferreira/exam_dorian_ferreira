package fr.dorian_ferreira.exam.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO extends UserUpdateDTO {

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    private LocalDate birthAt;
}
