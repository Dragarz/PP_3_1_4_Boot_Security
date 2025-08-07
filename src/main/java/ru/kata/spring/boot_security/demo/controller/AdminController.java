package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("users", userService.listUsers());
        response.put("currentUser", userService.getCurrentUser());
        return ResponseEntity.ok(response);
    }

    // Добавляем endpoint для получения одного пользователя
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        userService.addUser(
                userDto.getName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRoles()
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto userDto) {
        // Если пароль не указан, передаем null
        String password = userDto.getPassword().isEmpty() ? null : userDto.getPassword();

        userService.updateUser(
                id,
                userDto.getName(),
                userDto.getLastName(),
                userDto.getEmail(),
                password,  // Передаем null если пароль пустой
                userDto.getRoles()
        );
        return ResponseEntity.ok().build();
    }
}