package org.EmailNotification.controllers;

import org.EmailNotification.data.models.EmailDetail;
import org.EmailNotification.data.models.User;
import org.EmailNotification.dto.UserLoginRequest;
import org.EmailNotification.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/send")
    public EmailDetail sendEmail(@RequestBody EmailDetail emailDetail) {
        return userService.sendMail(emailDetail);
    }
}
