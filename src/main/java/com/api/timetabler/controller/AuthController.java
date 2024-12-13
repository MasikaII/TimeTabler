package com.api.timetabler.controller;

import com.api.timetabler.models.LoginRequest;
import com.api.timetabler.models.Users;
import com.api.timetabler.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("sign-up")
    public String signup (@RequestBody Users users) {
        return usersService.signup(users);
    }

//    @PostMapping("login")
//    public String login (@RequestBody String identifier, @RequestBody String password) {
//        return usersService.login(identifier, password);
//    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return usersService.login(loginRequest.getIdentifier(), loginRequest.getPassword());
    }
}
