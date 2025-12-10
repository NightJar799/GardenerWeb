package com.example.gardener.controller;

import com.example.gardener.DTO.TestDTO;
import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import com.example.gardener.service.PreferencesService;
import com.example.gardener.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class PlantListController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final UserService userService;
    private final PreferencesService preferencesService;

    public PlantListController(UserService userService, PreferencesService preferencesService) {
        this.userService = userService;
        this.preferencesService = preferencesService;
    }

    @GetMapping("/main")
    public String listPlantsGet(HttpSession httpSession,
                                Model model, HttpServletResponse response) {
        log.info("получаем главную страницу");
        User user = (User) httpSession.getAttribute("user");
        log.info(user.getLogin());
        if (user.getLogin() == null) return "redirect:/auth";
        if (redisTemplate.opsForList().indexOf("newUsers", user.getLogin()) == null) {
            log.info("URYAAAAAAAAAAAAAAAA");
            model.addAttribute("worker", userService.getUserIdByLogin(user.getLogin()));
            return "mainGarden";
        } else {
            redisTemplate.opsForList().remove("newUsers",1,user.getLogin());//(redisTemplate.opsForList().indexOf("newUsers", email),0, );
            return "redirect:/test";
        }
    }

    @GetMapping("/test")
    public String getTestPage(Model model) {
        log.info("тестик");
        model.addAttribute("test", new TestDTO());
        return "test";
    }

    @PostMapping("/test")
    public String postTest(@ModelAttribute("test") TestDTO testDTO,
                           HttpSession httpSession) {
        log.info("Отдаём тестики данные");
        User user = (User) httpSession.getAttribute("user");
        log.info(user.getLogin());
        preferencesService.createNewPreferences(user.getId(), testDTO);
        return "redirect:/main";
    }
}
