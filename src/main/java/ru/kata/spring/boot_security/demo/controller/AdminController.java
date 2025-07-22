package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }


    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String id) {
        try {
            userService.deleteUser(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        try {
            userService.addUser(userDto.getName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), userDto.getRoles());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Void> updateUser(@RequestParam String id, @RequestBody UserDto userDto) {
        try {
            userService.updateUser(Long.parseLong(id), userDto.getName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), userDto.getRoles());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
