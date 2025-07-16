package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.UserDaoImp;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Component
public class DataInitializer {
    private final UserDaoImp userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserDaoImp userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        //Добавляем админа с ролью ADMIN и USER
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        userRole.setRole("ROLE_USER");

        User admin = new User();
        admin.setUsername("admin");
        admin.setFirstName("Vasya");
        admin.setEmail("asd@sad.ru");
        admin.setLastName("Petrov");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(List.of(adminRole, userRole));
        userRole.setUser(admin);
        adminRole.setUser(admin);


        userRepository.add(admin);

        //Добавляем пользвателя user с ролью USER
        Role userRole2 = new Role();
        userRole2.setRole("ROLE_USER");

        User user = new User();
        user.setUsername("user");
        user.setFirstName("Vanya");
        user.setEmail("asd@sad.ru");
        user.setLastName("Petrov");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(List.of(userRole2));
        userRole2.setUser(user);

        userRepository.add(user);

    }
}
