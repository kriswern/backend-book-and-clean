package com.group3.bookandclean.controller;

import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByMail(@PathVariable String email) {
        return userService.findUserByEmail(email);
        //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

