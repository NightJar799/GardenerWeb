package com.example.gardener.config;

import com.example.gardener.Entities.User;
import com.example.gardener.Repository.UserRepository;
import com.example.gardener.Repository.UserRepositorySecurity;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Slf4j
@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           UserRepositorySecurity userRepo) throws Exception {
        return http
                .csrf(csrf -> csrf
                        // This is the DEFAULT, ensure it's not disabled
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // OR if using session-based tokens
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                )
                .authorizeHttpRequests((authorize) -> authorize
                        // Простые правила - проверка роли
                        .requestMatchers("/reg", "/reg/**")
                        .permitAll()
                        .requestMatchers("/auth", "/auth/**")
                        .permitAll()
                        .requestMatchers("/", "/**")
                        .authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/auth")
                        .loginProcessingUrl("/auth")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler(userRepo))
                        .failureUrl("/auth?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth?logout")
                        .permitAll())
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler(accessDeniedHandler()))
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            UserRepositorySecurity userRepository) {

        return (request, response, authentication) -> {
            log.info("=== AUTHENTICATION SUCCESS ===");
            log.info("User: {}", authentication.getName());
            log.info("Authorities: {}", authentication.getAuthorities());

            var authorities = authentication.getAuthorities();
            String username = authentication.getName();

            // Находим пользователя в базе
            User user = userRepository.findByUsername(username);

            Cookie cookie = new Cookie("email", user.getLogin());
            Cookie cookieId = new Cookie("id", String.valueOf(user.getId()));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            cookie.setMaxAge(30);
            cookieId.setMaxAge(30);
            response.addCookie(cookie);
            response.addCookie(cookieId);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

            if (user == null) {
                log.info("anyway something is wrong");
                response.sendRedirect("/auth?error=true");
                return;
            }

            //Integer userId = user.getId();

//            // Перенаправляем в зависимости от роли
//            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//                log.info("Redirecting ADMIN to /managers/{}", userId);
//                response.sendRedirect("/managers/" + userId);
//            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_WORKER"))) {
//                log.info("Redirecting WORKER to /workers/{}", userId);
//                response.sendRedirect("/workers/" + userId);
//            } else {
//                response.sendRedirect("/home");
//            }
            response.sendRedirect("/main");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            log.error("=== ACCESS DENIED ===");
            log.error("URL: {}", request.getRequestURI());
            log.error("Method: {}", request.getMethod());
            log.error("Query String: {}", request.getQueryString());
            log.error("Exception: {}", accessDeniedException.getMessage());

            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                log.error("User: {}", authentication.getName());
                log.error("Authenticated: {}", authentication.isAuthenticated());
                log.error("Authorities: {}", authentication.getAuthorities());
            } else {
                log.error("No authentication found");
            }
            log.error("=== END ACCESS DENIED ===");

            response.sendRedirect("/auth?error=true");
        };
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        log.info("Creating UserDetailsService bean...");

        return username -> {
            log.info("=== LOADING USER DETAILS ===");
            log.info("Username: {}", username);

            User user = userRepo.findUserIdByLogin(username);

            if (user != null) {
                log.info("User found in database: {}", user.getUsername());
                log.info("User password hash: {}", user.getPassword());
                //log.info("User roles: {}", user.getUserRole());
                log.info("=== END LOADING USER DETAILS ===");
                return user;
            }

            log.warn("User NOT FOUND in database: {}", username);
            log.info("=== END LOADING USER DETAILS ===");
            throw new UsernameNotFoundException("User " + username + " doesn't exist");
        };
    }
}

