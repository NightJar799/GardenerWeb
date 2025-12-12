package com.example.gardener.controller;

import com.example.gardener.DTO.PlantDTO;
import com.example.gardener.Entities.User;
import com.example.gardener.service.EditService;
import com.example.gardener.service.FavoriteService;
import com.example.gardener.service.PlantService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class PlantController {
    private final PlantService plantService;
    private final FavoriteService favoriteService;
    private final EditService editService;

    public PlantController(PlantService plantService, FavoriteService favoriteService, EditService editService) {
        this.plantService = plantService;
        this.favoriteService = favoriteService;
        this.editService = editService;
    }

    @GetMapping("/plant/{id}")
    public String showPlant(@PathVariable Integer id, HttpSession httpSession,
                            Model model) {



        model.addAttribute("plant", plantService.getPlantDTO(id));
        if (httpSession.getAttribute("user") != null) {
            log.info("NOT NULL");
            log.info(((User)httpSession.getAttribute("user")).getAuthorities().toString());
            User user = (User) httpSession.getAttribute("user");
            boolean isFavorite = favoriteService.isPlantInFavorites(user.getId(), id);
            model.addAttribute("isFavorite", isFavorite);
        }
        return "plant";
    }

    @PostMapping("/favorite/add")
    public String addToFavorite(@RequestParam Integer plantId,
                                @RequestParam Integer userId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        if (session.getAttribute("user") == null) {
            redirectAttributes.addFlashAttribute("error", "Please login to add favorites");
            return "redirect:/auth";
        }

        favoriteService.addToFavorites(userId, plantId);
        redirectAttributes.addFlashAttribute("message", "Plant added to favorites!");
        return "redirect:/plant/" + plantId;
    }

    @PostMapping("/plant/{id}/update")
    public String changePlant(@ModelAttribute("PlantChar") PlantDTO plantDTO) {
        log.info("try to change plant");
        editService.updatePlantInfo(plantDTO);
        return "redirect:/plant/" + plantDTO.getPlantId();
    }

    @PostMapping("plant/{id}/delete")
    public String deletePlant(@PathVariable("id") Integer id) {
        log.info("delete plant " + id);
        editService.deletePlantById(id);
        return "redirect:/main";
    }
}
