package fr.dorian_ferreira.exam.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.dorian_ferreira.exam.json_views.JsonViewsUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @JsonView(JsonViewsUser.Uuid.class)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonView(JsonViewsUser.Email.class)
    @Column(nullable = false)
    private String email;

    @JsonView(JsonViewsUser.Username.class)
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonView(JsonViewsUser.Avatar.class)
    private String avatar;

    @JsonView(JsonViewsUser.BirthedAt.class)
    private LocalDate birthedAt;

    @JsonView(JsonViewsUser.CreatedAt.class)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsUser.Level.class)
    @Column(nullable = false)
    private Integer level = 1;

    @Column(nullable = false)
    private String roles;

    @JsonView(JsonViewsUser.Games.class)
    @OneToMany(mappedBy = "user")
    private List<Game> games = new ArrayList<>();

    @JsonView(JsonViewsUser.IsAdmin.class)
    public boolean isAdmin() {
        return roles.contains("ADMIN");
    }

    @JsonView(JsonViewsUser.Active.class)
    public boolean isActive() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("USER"));

        if(roles.contains("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
