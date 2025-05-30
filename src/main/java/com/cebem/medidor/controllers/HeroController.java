package com.cebem.medidor.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.medidor.models.Hero;
import com.cebem.medidor.services.HeroService;

import jakarta.annotation.PostConstruct;

@Controller
public class HeroController {
    private final HeroService heroService;
    private Hero currentHero;
    private boolean guessedCorrectly = false;
    private Set<Integer> guessedHeroIds = new HashSet<>(); // Guarda IDs de héroes adivinados
    private List<Hero> allHeroes; // Lista completa de héroes

    @PostConstruct
    public void init() {
        allHeroes = heroService.getAllHeroes(); // Carga todos los héroes al inicio
    }

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
        if (guessedCorrectly) {
            guessedHeroIds.add(currentHero.getId()); // Registra el héroe adivinado
        }
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

    @GetMapping("/progress")
    public String showProgress(Model model) {
        model.addAttribute("guessedHeroes", allHeroes.stream()
                .filter(h -> guessedHeroIds.contains(h.getId()))
                .collect(Collectors.toList()));
        model.addAttribute("missingHeroes", allHeroes.stream()
                .filter(h -> !guessedHeroIds.contains(h.getId()))
                .collect(Collectors.toList()));
        return "progress";
    }
}