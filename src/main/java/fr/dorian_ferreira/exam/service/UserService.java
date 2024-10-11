package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.user.UserCreateDTO;
import fr.dorian_ferreira.exam.entity.User;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.UserRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import fr.dorian_ferreira.exam.service.utils.FileUploaderService;
import fr.dorian_ferreira.exam.slugger.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService implements
        UserDetailsService,
        CreateServiceInterface<User, UserCreateDTO>,
        ReadOneByIdServiceInterface<User, String>
{
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final Slugger slugger;

    @Override
    public User create(UserCreateDTO userDto) {
        User user = dtoToObject(userDto, new User());

        user.setCreatedAt(LocalDateTime.now());
        user.setRoles("[\"ROLE_USER\"]");

        return userRepository.saveAndFlush(user);
    }

    private User dtoToObject(UserCreateDTO userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setBirthedAt(userDto.getBirthedAt());
        user.setUsername(userDto.getUsername());
        user.setAvatar(userDto.getAvatar());

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = findByEmail(username);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User"));
    }

    @Override
    public User findOneById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User"));
    }

    public User findByPrincipal(Principal principal) {
        return findByEmail(principal.getName());
    }

    public Boolean uploadImage(MultipartFile file, Principal principal) {
        if (principal != null) {
        User user = findByEmail(principal.getName());
        FileUploaderService fileUploaderService = new FileUploaderService(slugger);
        String filename = fileUploaderService.save(file, "uploads/user");
        if (filename != null) {
            user.setAvatar(filename);
            userRepository.saveAndFlush(user);
            return true;
        }
    }
        return false;
    }
}
