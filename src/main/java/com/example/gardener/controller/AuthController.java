package com.example.gardener.controller;

import com.example.gardener.Entities.User;
import com.example.gardener.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
        log.debug("Отображение главной аутенфикации");
        return "index";
    }

    @PostMapping("/auth")
    public String auth(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        log.debug("Попытка авторизоваться");
        Map<String, String> loginAndPassword = userService.getAllPasswordsAndLogins();
        if (!loginAndPassword.containsKey(email) || !loginAndPassword.get(email).equals(password)) {
            return "badlogin";
        }
        System.out.println(email);
        System.out.println(password);
        User user = userService.getUserIdByLogin(email);
        Cookie cookie = new Cookie("email", email);
        Cookie cookieId = new Cookie("id", String.valueOf(user.getId()));
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        cookie.setMaxAge(30);
        cookieId.setMaxAge(30);
        response.addCookie(cookie);
        response.addCookie(cookieId);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "redirect:/";
    }
}