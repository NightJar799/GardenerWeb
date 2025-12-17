package com.example.gardener.controller;

import com.example.gardener.DTO.PlantDTO;
import com.example.gardener.service.EditService;
import org.springframework.ui.Model;
import com.example.gardener.DTO.UserPageDTO;
import com.example.gardener.Entities.User;
import com.example.gardener.service.FavoriteService;
import com.example.gardener.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final EditService editService;

    public UserController(UserService userService, FavoriteService favoriteService, EditService editService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.editService = editService;
    }

    @GetMapping("/user")
    public String getUserPage(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/auth";
        }

        UserPageDTO userPageDTO = userService.getUserPageData(sessionUser.getId());
        model.addAttribute("user", userPageDTO);

        model.addAttribute("climateOptions", Arrays.asList("tropical", "dry", "temperate", "continental", "polar"));
        model.addAttribute("soilOptions", Arrays.asList("sandy", "peaty", "loamy", "silty", "clay", "chalky"));
        model.addAttribute("spaceOptions", Arrays.asList(10, 25, 50, 100, 250));
        model.addAttribute("waterOptions", Arrays.asList("none", "lake", "river", "sea/ocean"));
        model.addAttribute("landscapeOptions", Arrays.asList("mountain", "forest", "steppe", "tundra", "sandy", "hilly"));

        return "user";
    }

    @PostMapping("/user/preferences/update")
    public String updatePreferences(@RequestParam(required = false) String climate,
                                    @RequestParam(required = false) String soil,
                                    @RequestParam(required = false) Integer space,
                                    @RequestParam(required = false) String water,
                                    @RequestParam(required = false) String landScape,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/auth";
        }

        userService.updateUserPreferences(sessionUser.getId(), climate, soil, space, water, landScape);

        redirectAttributes.addFlashAttribute("message", "Preferences updated successfully!");
        return "redirect:/user";
    }

    @PostMapping("/favorite/remove")
    public String removeFavorite(@RequestParam Integer plantId,
                                 @RequestParam Integer userId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null || !sessionUser.getId().equals(userId)) {
            return "redirect:/auth";
        }

        log.info("Great cleansing");
        favoriteService.removeFromFavorites(userId, plantId);
        redirectAttributes.addFlashAttribute("message", "Plant removed from favorites!");
        return "redirect:/user";
    }

    @GetMapping("user/addPlant")
    public String getAddPlant(Model model){
        model.addAttribute("plant", new PlantDTO());
        return "addPlant";
    }

    @PostMapping("user/addPlant")
    public String getAddPlant(@ModelAttribute("plant") PlantDTO plantDTO, Model model) {
        log.info(plantDTO.getName());
        if (!editService.isPlantDTOFull(plantDTO)) {
            log.info("Problem Fields");
            model.addAttribute("errorAdd", true);
            return "addPlant";
        }
        editService.addPlant(plantDTO);
        return "redirect:/main";
    }
}
