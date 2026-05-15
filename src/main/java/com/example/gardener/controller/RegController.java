package com.example.gardener.controller;

import com.example.gardener.DTO.RegisterDTO;
import com.example.gardener.Entities.User;
import com.example.gardener.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String regPage(Model model) {
        model.addAttribute("reg", new RegisterDTO());
        log.debug("Отображение страницы регистрации");
        return "registry";
    }

    @PostMapping("/reg")
    public String registry(@ModelAttribute("reg") RegisterDTO registerDTO, Model model) {
        log.debug("Попытка Регистрации");
        if (registerDTO.getEmail().split("@").length != 2) {
            model.addAttribute("emailError", true);
            return "registry";
        }
        if (!registerDTO.getPassword().equals(registerDTO.getPasswordCheck())) {
            model.addAttribute("passwordError", true);
            return "registry";
        }
        if (!registerDTO.getPhone().matches("^\\+?[1-9]\\d{1,14}$")) {
            model.addAttribute("phoneError", true);
            return "registry";
        }
        redisTemplate.opsForList().leftPush("newUsers", registerDTO.getEmail());
        System.out.println(userService.addUser(userService.mapRegToEntity(registerDTO)));
        log.debug("Попытка Регистрации успешна");
        return "redirect:/auth";
    }
}
