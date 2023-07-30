package com.example.practice.controller;

import com.example.practice.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello() {
        User user = new User();
        user.setName("steve");
        user.setAge(10);
        return user;
    }

}
