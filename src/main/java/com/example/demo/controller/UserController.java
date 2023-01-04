package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String welcome() {
        return "welcome";
    }


    @GetMapping(value = "shawAllUsers")
    public String allUsersShaw(ModelMap model) {
        model.addAttribute("UsersAll", userService.getAllUser());
        return "shawAllUsers";
    }

    @GetMapping(value = "users/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("UserToAdd", user);
        return "addUser";
    }

    @PostMapping(value = "users/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/shawAllUsers";
    }

    @GetMapping(value = "users/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("UserEdit", user);
        return "editUser";
    }

    @PostMapping(value = "users/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/shawAllUsers";
    }

    @GetMapping("users/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/shawAllUsers";
    }

}