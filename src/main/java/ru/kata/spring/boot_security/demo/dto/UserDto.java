package ru.kata.spring.boot_security.demo.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Set<String> roles;
}