package com.example.gardener.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PlantListController {
    @GetMapping("/")
    public String listPlantsGet() {
        log.info("получаем главную страницу");
        return "mainGarden";
    }
}
