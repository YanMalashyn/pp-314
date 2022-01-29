package com.example.springcrudsecurityboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/")
    public String start() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String start2() {
        return "redirect:/login";
    }




}
