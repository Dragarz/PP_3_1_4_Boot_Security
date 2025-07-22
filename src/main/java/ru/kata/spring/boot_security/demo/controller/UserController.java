package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {
    private final UserService userService;


    @GetMapping(value = "/")
    public String printUsers() {
        return "index";
    }


    @GetMapping(value = "/user")
    public String userPage(ModelMap model) {
        var users = userService.listUsers();
        model.addAttribute("users", users);
        return "user";
    }
}