package ru.kata.spring.boot_security.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() { return userRepository.findAll(); }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public void addUser(String name, String lastName, String email, String password, String[] rolesArr) {
        Set<Role> roleSet = Arrays.stream(rolesArr)
                .map(roleCode -> roleRepository.findByRole("ROLE_" + roleCode))
                .collect(Collectors.toSet());
        User user = new User(name, lastName, email, passwordEncoder.encode(password), roleSet);
        userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(long id, String name, String lastName, String email, String password, String[] rolesArr) {
        User user = userRepository.findById(id).get();

        if(rolesArr.length > 0) {
            Set<Role> roleSet = Arrays.stream(rolesArr)
                    .map(roleRepository::findByRole)
                    .collect(Collectors.toSet());

            user.setRoles(roleSet);
        }

        user.setUsername(name);
        user.setLastName(lastName);
        user.setEmail(email);
        if(passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(user);
    }
}
