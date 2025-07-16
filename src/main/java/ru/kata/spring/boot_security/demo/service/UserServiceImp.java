package ru.kata.spring.boot_security.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class UserServiceImp implements UserService{
    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() { return userDao.listUsers(); }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(String name, String lastName, String email) {
        userDao.add(new User(name, lastName, email));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(long id, String name, String lastName, String email) {
        userDao.updateUser(id, name, lastName, email);
    }

}
