package com.example.gardener.controller;

import org.springframework.ui.Model;
import com.example.gardener.DTO.UserPageDTO;
import com.example.gardener.Entities.User;
import com.example.gardener.service.FavoriteService;
import com.example.gardener.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;
    private final FavoriteService favoriteService;

    public UserController(UserService userService, FavoriteService favoriteService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/user")
    public String getUserPage(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/auth";
        }

        UserPageDTO userPageDTO = userService.getUserPageData(sessionUser.getId());
        model.addAttribute("user", userPageDTO);

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
}
