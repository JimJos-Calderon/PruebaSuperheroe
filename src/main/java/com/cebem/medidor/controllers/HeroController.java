package com.cebem.medidor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.medidor.models.Hero;
import com.cebem.medidor.services.HeroService;

@Controller
public class HeroController {
    private final HeroService heroService;
    private Hero currentHero;
    private boolean guessedCorrectly = false;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
        this.currentHero = heroService.getRandomHero();
    }

    @GetMapping("/guess-hero")
    public String getHeroToGuess(Model model) {
        model.addAttribute("hero", currentHero);
        model.addAttribute("guessedCorrectly", guessedCorrectly);
        return "guess-hero";
    }

    @PostMapping("/guess-hero")
    public String checkGuess(@RequestParam String guess, Model model) {
        guessedCorrectly = guess.equalsIgnoreCase(currentHero.getLocalized_name());
        model.addAttribute("hero", currentHero);
        model.addAttribute("guessedCorrectly", guessedCorrectly);
        return "guess-hero";
    }

    @GetMapping("/new-hero")
    public String getNewHero() {
        currentHero = heroService.getRandomHero();
        guessedCorrectly = false;
        return "redirect:/guess-hero";
    }
}