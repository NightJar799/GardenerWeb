package com.example.gardener.controller;

import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import com.example.gardener.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class PlantListController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final UserService userService;

    public PlantListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listPlantsGet(@CookieValue(value = "email") String email, @CookieValue(value = "id") String id,
                                HttpServletResponse response) {
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        log.info("получаем главную страницу");
        if (email == null) return "redirect:/auth";
//        List<Object> users = redisTemplate.opsForList().range("newUsers", 0, -1);
        if (redisTemplate.opsForList().indexOf("newUsers", email) == null) {
            log.info("URYAAAAAAAAAAAAAAAA");
            return "mainGarden";
        } else {
            //Cookie cookie = new Cookie("id", id);
            redisTemplate.opsForList().remove("newUsers",1,email);//(redisTemplate.opsForList().indexOf("newUsers", email),0, );
            return "redirect:/test";
        }
    }

    @GetMapping("/test")
    public String getTestPage() {
        log.info("тестик");
        return "test";
    }

    @PostMapping("/test")
    public String postTest(@RequestParam String climate, @RequestParam String soil, @RequestParam String space,
                           @RequestParam String water, @RequestParam String landscape, @CookieValue(value = "email") String login,
    @CookieValue(value = "id") String id) {
        log.info("Отдаём тестики данные");
        User user = userService.getUserIdByLogin(login);
        if (space.equals("more")) space = String.valueOf(Integer.MAX_VALUE);
        Preferences preferences = new Preferences(user, climate, soil, Integer.parseInt(space), water, landscape);
        userService.addNewPreference(preferences);
        return "redirect:/";
    }
}
