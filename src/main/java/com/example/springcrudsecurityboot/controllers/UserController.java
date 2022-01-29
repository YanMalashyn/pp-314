package com.example.springcrudsecurityboot.controllers;


import com.example.springcrudsecurityboot.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String getUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user/userInfo";
    }

}
