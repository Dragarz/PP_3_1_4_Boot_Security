package ru.kata.spring.boot_security.demo.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.SecurityUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof SecurityUser) {
            return ((SecurityUser) principal).getUser(); // Метод getUser() должен быть в SecurityUser
        } else {
            return null;
        }
    }
}
