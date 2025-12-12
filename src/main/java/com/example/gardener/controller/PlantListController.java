package com.example.gardener.controller;

import com.example.gardener.DTO.PlantListDTO;
import com.example.gardener.DTO.TestDTO;
import com.example.gardener.Entities.User;
import com.example.gardener.service.PlantService;
import com.example.gardener.service.PreferencesService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
public class PlantListController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final PreferencesService preferencesService;
    private final PlantService plantService;

    public PlantListController(PreferencesService preferencesService, PlantService plantService) {
        this.preferencesService = preferencesService;
        this.plantService = plantService;
    }

    @GetMapping("/main")
    public String listPlantsGet(@RequestParam(required = false, defaultValue = "none") String sortBy,
                                HttpSession httpSession,
                                Model model, HttpServletResponse response) {
        log.info("получаем главную страницу");
        User user = (User) httpSession.getAttribute("user");
        log.info(user.getLogin());
        if (user.getLogin() == null) return "redirect:/auth";
        if (redisTemplate.opsForList().indexOf("newUsers", user.getLogin()) == null) {
            List<PlantListDTO> plants = plantService.getPlantAllForListDTO();
            switch (sortBy) {
                case "name_asc":
                    plants.sort(Comparator.comparing(PlantListDTO::getName));
                    break;
                case "name_desc":
                    plants.sort(Comparator.comparing(PlantListDTO::getName).reversed());
                    break;
                case "space_asc":
                    plants.sort(Comparator.comparing(PlantListDTO::getSpace));
                    break;
                case "space_desc":
                    plants.sort(Comparator.comparing(PlantListDTO::getSpace).reversed());
                    break;
                case "climate_asc":
                    plants.sort(Comparator.comparing(PlantListDTO::getClimate));
                    break;
                case "climate_desc":
                    plants.sort(Comparator.comparing(PlantListDTO::getClimate).reversed());
                    break;
            }

            model.addAttribute("plantList", plants);

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
