package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {
    List<User> listUsers();

    User getCurrentUser();

    void deleteUser(long userId);

    void addUser(String name, String lastName, String email, String password, String[] roles);

    void updateUser(long id, String name, String lastName, String email, String password, String[] roles);
}
