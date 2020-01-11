package com.example.ais.controller;

import com.example.ais.entity.User;
import com.example.ais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/main")
    public String index(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "index";
    }

    @PostMapping(path = "/add")
    public String add(
            @RequestParam String name,
            @RequestParam String surname){
        User user = new User(name, surname);
        userRepository.save(user);
        return "redirect:/users/main";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(
            @PathVariable(name = "id") Long id){
        userRepository.deleteById(id);
        return "redirect:/users/main";
    }

}
