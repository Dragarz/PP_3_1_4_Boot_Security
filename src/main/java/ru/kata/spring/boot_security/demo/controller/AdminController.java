package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @GetMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }
}
