package com.example.practice.controller;

import com.example.practice.dto.Req;
import com.example.practice.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/naver")
    public String naver() {

        String query = "갈비";

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", query)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "")
                .header("X-Naver-Client-Secret", "")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }

    @GetMapping("/hello")
    public User getHello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
            @RequestBody Req<User> user,
            @PathVariable int userId,
            @PathVariable String userName,
            @RequestHeader("x-authorization") String authorization,
            @RequestHeader("custom-header") String customHeader
    ) {
        log.info("client req : {}", user);
        log.info("authorization : {}, custom : {}", authorization, customHeader);
        log.info("userId : {}, userName : {}", userId, userName);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResponseBody(user.getResponseBody());

        return response;
    }

}
