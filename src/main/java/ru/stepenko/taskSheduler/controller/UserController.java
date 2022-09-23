package ru.stepenko.taskSheduler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.stepenko.taskSheduler.model.User;
import ru.stepenko.taskSheduler.service.UserServiceImpl;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/create-user")
    public void create(@RequestBody User user) {
        service.create(user);
    }
}
