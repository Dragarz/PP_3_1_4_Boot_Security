package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String[] roles;

}