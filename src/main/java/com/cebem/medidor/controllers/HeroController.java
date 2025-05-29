package com.cebem.medidor.controllers;
// HeroController.java - Controlador para manejar las peticiones
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cebem.medidor.models.Hero;
import com.cebem.medidor.services.HeroService;

@Controller
public class HeroController {
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/random-hero")
    public String getRandomHero(Model model) {
        Hero hero = heroService.getRandomHero();
        model.addAttribute("hero", hero);
        return "hero-card";
    }
}