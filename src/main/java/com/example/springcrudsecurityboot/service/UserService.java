package com.example.springcrudsecurityboot.service;



import com.example.springcrudsecurityboot.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user, Long id);

    List<User> getListUsers();

    User getUserById(Long id);

    void updateUser(Long id, User user, Long idRole);

    void deleteUser(Long id);

    User findByUsername(String username);
}

