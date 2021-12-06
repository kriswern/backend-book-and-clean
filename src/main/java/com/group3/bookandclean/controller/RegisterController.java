package com.group3.bookandclean.controller;

import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
    }
}
