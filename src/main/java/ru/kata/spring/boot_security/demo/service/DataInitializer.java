package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import java.util.Set;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    @Transactional
    public void init() {
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        userRole.setRole("ROLE_USER");

        Set<Role> roles = Set.of(adminRole,userRole);

        User admin = new User("admin", "Petrov", "asd@sad.ru", passwordEncoder.encode("admin"), roles);

        User user = new User("user", "Petrov", "asd@sad.ru", passwordEncoder.encode("user"), Set.of(userRole));
        userRepository.saveAllAndFlush(List.of(user, admin));
    }
}
