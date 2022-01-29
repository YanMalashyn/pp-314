package com.example.springcrudsecurityboot.service;


import com.example.springcrudsecurityboot.Repository.UserRepository;
import com.example.springcrudsecurityboot.model.Role;
import com.example.springcrudsecurityboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleService roleService;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;





    @Override
    public void saveUser(User user, Long id) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getRoleById(id));
        user.setRoles(roleSet);
        userRepository.save(user);

    }

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(Long id, User user, Long idRole) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getRoleById(idRole));
        user.setRoles(roleSet);
//
//        User userToUpdate = userRepository.getById(user.getId());
//        userToUpdate.setUsername(user.getUsername());
//        userToUpdate.setPassword(user.getPassword());
//        userToUpdate.setFirstName(user.getFirstName());
//        userToUpdate.setLastName((user.getLastName()));
//        userToUpdate.setAge(user.getAge());
//        userToUpdate.setRoles(user.getRoles());

        userRepository.save(user);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
