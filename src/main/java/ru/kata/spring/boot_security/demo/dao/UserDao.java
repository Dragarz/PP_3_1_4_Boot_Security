package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDao {
    void add(User user);
    List<User> listUsers();

    void deleteUser(long userId);

    void updateUser(long userId, String name, String lastName, String email);

    User findByUsername(String username);
}
