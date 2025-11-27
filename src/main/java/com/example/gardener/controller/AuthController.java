package com.example.gardener.controller;

import com.example.gardener.DTO.ShowEmployeeInfoDto;
import com.example.gardener.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public String homePage() {
        log.debug("Отображение главной страницы");
        return "index";
    }

    @PostMapping("/auth")
    public String showAllEmployees(@RequestParam String email, @RequestParam String password) {
        log.debug("Попытка авторизоваться");
        Map<String, String> loginAndPassword = userService.getAllPasswordsAndLogins();
        if (!loginAndPassword.containsKey(email) || !loginAndPassword.get(email).equals(password)) {
            return "badlogin";
        }
        return "redirect:/";
    }
}