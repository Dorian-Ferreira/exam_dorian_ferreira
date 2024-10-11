package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.user.UserCreateDTO;
import fr.dorian_ferreira.exam.entity.User;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.UserRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements
        UserDetailsService,
        CreateServiceInterface<User, UserCreateDTO>,
        ReadOneByIdServiceInterface<User, String>
{
    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreateDTO userDto) {
        User user = dtoToObject(userDto, new User());

        user.setCreatedAt(LocalDateTime.now());
        user.setRoles("[\"ROLE_USER\"]");

        return userRepository.saveAndFlush(user);
    }

    private User dtoToObject(UserCreateDTO userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBirthedAt(userDto.getBirthedAt());
        user.setUsername(userDto.getUsername());
        user.setAvatar(userDto.getAvatar());

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = optionalUser.get();

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
}
