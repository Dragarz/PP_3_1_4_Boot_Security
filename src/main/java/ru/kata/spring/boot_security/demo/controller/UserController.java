package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {
    private final UserService userService;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }
}