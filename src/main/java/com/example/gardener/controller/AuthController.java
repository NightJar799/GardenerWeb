package com.example.gardener.controller;

import com.example.gardener.DTO.ShowEmployeeInfoDto;
import com.example.gardener.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/auth")
    public String homePage() {
        log.debug("Отображение главной страницы");
        return "index";
    }

    @PostMapping("/auth")
    public String showAllEmployees(Model model) {
        log.debug("Отображение списка всех сотрудников");
        ArrayList<ShowEmployeeInfoDto> list = new ArrayList<>();
        list.add(new ShowEmployeeInfoDto("assad", "asdas", "sdfafsd", LocalDate.now()));
        model.addAttribute("allEmployees", list);
        return "employee-all";
    }
}