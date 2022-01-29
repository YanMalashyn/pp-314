package com.example.springcrudsecurityboot.controllers;


import com.example.springcrudsecurityboot.model.User;
import com.example.springcrudsecurityboot.service.RoleService;
import com.example.springcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    //Get /admin/index/ - вывод в таблицу всех пользователей
    @GetMapping()
    public String index (Model model){
        model.addAttribute("userList", userService.getListUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/index";
    }

    //Get /admin/userInfo/ - вывод данных только об одном пользователе
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleUser", userService.getListUsers());
        return "admin/userInfo";
    }

    //GET /admin/newUser/ - вывод формы для записи нового пользователя
    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("roleList", roleService.getAllRoles());
        return "admin/newUser";
    }

    //POST /admin/ - сохранение данных ввода и редирект на таблицу пользователей
    @PostMapping("/user/add")
    public String addNewUser(@ModelAttribute("newUser")  User newUser,
                             @RequestParam(value = "rolesIdSelect") Long idRole) {
        userService.saveUser(newUser, idRole);
        return "redirect:/admin/";
    }

    //GET /admin/ - удаление пользователя по id и редирект на таблицу пользователя
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

    //GET /admin/edit/ - вывод формы для редактирования уже созданного пользователя
    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleList", roleService.getAllRoles());
        return "admin/editUser";
    }

    //PATCH /admin/user/id - сохранение введенных данных и редирект на страницу с информацией о пользователе
    @PostMapping("/user/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user")  User user,
                           @RequestParam(value = "rolesIdSelect") Long idRole) {
        System.out.println("pered update");
        userService.updateUser(id, user, idRole);
        System.out.println("after update");
        return "redirect:/admin/user/"+id;
    }
}
