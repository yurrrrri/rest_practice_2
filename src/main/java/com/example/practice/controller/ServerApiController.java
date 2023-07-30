package com.example.practice.controller;

import com.example.practice.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User getHello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(
            @RequestBody User user,
            @PathVariable int userId,
            @PathVariable String userName
    ) {
        log.info("client req : {}", user);
        log.info("userId : {}, userName : {}", userId, userName);

        return user;
    }

}
