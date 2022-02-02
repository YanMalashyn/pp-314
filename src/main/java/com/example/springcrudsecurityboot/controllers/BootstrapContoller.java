package com.example.springcrudsecurityboot.controllers;

import com.example.springcrudsecurityboot.model.User;
import com.example.springcrudsecurityboot.service.RoleService;
import com.example.springcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("bootstrap")
public class BootstrapContoller {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public BootstrapContoller(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/index")
    public String index (Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal){
        model.addAttribute("user",principal);
        model.addAttribute("userList", userService.getListUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "bootstrap/index";
    }

    @GetMapping("/user")
    public String userPage (Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal){
        model.addAttribute("user",principal);
        model.addAttribute("userList", userService.getListUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "bootstrap/user";
    }




    @PostMapping("/add")
    public String saveUser (@ModelAttribute("user") User user,
                            @RequestParam("FirstName") String firstName,
                            @RequestParam("LastName") String lastName,
                            @RequestParam("Email") String email,
                            @RequestParam("Password") String password,
                            @RequestParam(value = "nameRoles") Long idRole) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(email);
        user.setPassword(password);
        userService.saveUser(user, idRole);
        return "redirect:index";
    }

    @PostMapping("/edite")
    public String editUser (@ModelAttribute("user") User user,
                            @RequestParam("Id") Long id,
                            @RequestParam("FirstName") String firstName,
                            @RequestParam("LastName") String lastName,
                            @RequestParam("Email") String email,
                            @RequestParam("Password") String password,
                            @RequestParam(value = "nameRoles") Long idRole) {
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(email);
        user.setPassword(password);
        userService.updateUser(user.getId(),user, idRole);
        return "redirect:index";
    }

    @PostMapping ("/delete")
    public String deleteUser (@RequestParam("Id") Long id) {
        userService.deleteUser(id);
        return "redirect:index";
    }






}
