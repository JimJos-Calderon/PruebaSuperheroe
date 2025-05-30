package com.cebem.medidor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.medidor.models.GiphyResponse;
import com.cebem.medidor.services.GiphyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GiphyController {
    private final GiphyService giphyService;
    
    public GiphyController(GiphyService giphyService) {
        this.giphyService = giphyService;
    }
    
    @GetMapping("/gifs")
    public String home(Model model) {
        GiphyResponse trendingGifs = giphyService.getTrendingGifs(12);
        model.addAttribute("gifs", trendingGifs.getData());
        model.addAttribute("searchMode", false);
        return "gifs";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        log.info("Buscando GIFs para: {}", query);
        GiphyResponse searchResults = giphyService.searchGifs(query, 12);
        model.addAttribute("gifs", searchResults.getData());
        model.addAttribute("searchMode", true);
        model.addAttribute("query", query);
        return "gifs";
    }
}