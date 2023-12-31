package ru.javamentor.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.springmvc.model.User;
import ru.javamentor.springmvc.service.UserService;
@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String printUserList(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping(value = "/edit")
    public String printUserById(ModelMap model, @RequestParam(name = "index", required = false) Long index) {
        model.addAttribute("user", userService.findById(index));
        return "user-info";
    }

    @PatchMapping(value = "/edit")
    public String editUserById(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/delete")
    public String deleteUserById(@RequestParam(name = "index", required = false) Long index) {
        userService.delete(index);
        return "redirect:/";
    }


    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PatchMapping
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() == null) {
            userService.save(user);
        } else {
            userService.update(user);
        }
        return "redirect:/";
    }
}
