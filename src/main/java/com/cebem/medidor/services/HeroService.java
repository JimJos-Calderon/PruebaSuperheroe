package com.cebem.medidor.services;
import java.util.Random;

// HeroService.java - Servicio para obtener datos de la API
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.medidor.models.Hero;

@Service
public class HeroService {
    private final RestTemplate restTemplate;
    private final Random random = new Random();

    public HeroService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Hero getRandomHero() {
        Hero[] heroes = restTemplate.getForObject(
            "https://api.opendota.com/api/heroes", Hero[].class);
        
        if (heroes == null || heroes.length == 0) {
            throw new RuntimeException("No se pudieron obtener los héroes");
        }
        
        return heroes[random.nextInt(heroes.length)];
    }
}