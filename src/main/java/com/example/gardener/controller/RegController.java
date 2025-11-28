package com.example.gardener.controller;

import com.example.gardener.Entities.User;
import com.example.gardener.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class RegController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String regPage() {
        log.debug("Отображение страницы регистрации");
        return "registry";
    }

    @PostMapping("/reg")
    public String registry(@RequestParam String email, @RequestParam String password, @RequestParam String passwordCheck,
                           @RequestParam String phone, @RequestParam(required = false) String nickname) {
        log.debug("Попытка Регистрации");
        if (!password.equals(passwordCheck)) {
            return "BadPasswords";
        }
        List<User> users = userService.getAllUsers();
        for (User user : users) if (user.getLogin().equals(email) || user.getPhone().equals(phone)) return "BadLoginReg";
        if (nickname == null) nickname = email.substring(0, email.indexOf("@"));
        User addedUser = new User(email, password, nickname, phone);
        redisTemplate.opsForList().leftPush("newUsers", email);
        System.out.println(userService.addUser(addedUser));
        log.debug("Попытка Регистрации успешна");
        return "redirect:/auth";
    }
}
