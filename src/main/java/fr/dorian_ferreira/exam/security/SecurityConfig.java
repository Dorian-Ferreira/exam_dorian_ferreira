package fr.dorian_ferreira.exam.security;

import fr.dorian_ferreira.exam.route.UrlRoute;
import fr.dorian_ferreira.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtTokenFilter jwtTokenFilter;
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        AntPathRequestMatcher.antMatcher(UrlRoute.LOGIN),
                        AntPathRequestMatcher.antMatcher(UrlRoute.REGISTER),
                        AntPathRequestMatcher.antMatcher(UrlRoute.MAP_BEST),
                        AntPathRequestMatcher.antMatcher(UrlRoute.GAME_SCORES),
                        AntPathRequestMatcher.antMatcher(UrlRoute.GAME_LAST)
                    ).permitAll()

                    .requestMatchers(
                        AntPathRequestMatcher.antMatcher(UrlRoute.BASE_MAP + "/**"),
                        AntPathRequestMatcher.antMatcher(UrlRoute.BASE_GAME + "/**"),
                        AntPathRequestMatcher.antMatcher(UrlRoute.BASE_ROUND + "/**"),
                        AntPathRequestMatcher.antMatcher(UrlRoute.BASE_COORDINATE + "/**")
                    ).authenticated()
//
//                    .requestMatchers(
//                            AntPathRequestMatcher.antMatcher("/api/**")
//                    ).hasRole("ADMIN")
            );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
