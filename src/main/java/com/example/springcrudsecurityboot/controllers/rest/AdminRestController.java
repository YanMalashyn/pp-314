package com.example.springcrudsecurityboot.controllers.rest;


import com.example.springcrudsecurityboot.exception_handling.NoSuchUserIdException;
import com.example.springcrudsecurityboot.model.User;
import com.example.springcrudsecurityboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getListUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        if (userService.getUserById(id) == null){
            throw new NoSuchUserIdException("Not find User with id " + id + " in data base");
        }
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user == null){
            throw new NoSuchUserIdException("Not find User with id " + id + " in data base");
        } else {
            userService.deleteUser(id);
            return "User with id " + id + " has been deleted";
        }
    }

}
