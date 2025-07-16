package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public class UserDaoImp implements UserDao{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void add(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> listUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        entityManager.getTransaction().commit();
        return users;
    }

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.username = :username", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public void deleteUser(long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(long userId, String name, String lastName, String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }
}
