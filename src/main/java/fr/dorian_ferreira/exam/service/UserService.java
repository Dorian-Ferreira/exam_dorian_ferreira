package fr.dorian_ferreira.exam.service;

import fr.dorian_ferreira.exam.dto.user.UserCreateDTO;
import fr.dorian_ferreira.exam.dto.user.UserUpdateDTO;
import fr.dorian_ferreira.exam.entity.User;
import fr.dorian_ferreira.exam.exception.EntityNotFoundException;
import fr.dorian_ferreira.exam.repository.UserRepository;
import fr.dorian_ferreira.exam.service.interfaces.CreateServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.DeleteServiceInterface;
import fr.dorian_ferreira.exam.service.interfaces.ReadOneByIdServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements
        UserDetailsService,
        CreateServiceInterface<User, UserCreateDTO>,
        DeleteServiceInterface<String>,
        ReadOneByIdServiceInterface<User, String>
{
    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreateDTO userDto) {
        User user = dtoToObject(userDto, new User());

        return userRepository.saveAndFlush(user);
    }

    public User update(UserUpdateDTO userDto, Principal principal) {
        User user = dtoUpdateToObject(userDto, findByEmail(principal.getName()));

        return userRepository.saveAndFlush(user);
    }

    public User dtoToObject(UserCreateDTO userCreateDTO, User user) {
        user.setEmail(userCreateDTO.getEmail());

        return dtoUpdateToObject(userCreateDTO, user);
    }

    private User dtoUpdateToObject(UserUpdateDTO userUpdateDTO, User user) {
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));

        return user;
    }

    @Override
    public boolean delete(String id) {
        User user = findOneById(id);

        user.setEmail("deleted@user.com");

        userRepository.saveAndFlush(user);
        return true;
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
}
