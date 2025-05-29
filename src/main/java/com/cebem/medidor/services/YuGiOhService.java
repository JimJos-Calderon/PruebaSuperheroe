package com.cebem.medidor.services;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.medidor.models.Card;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class YuGiOhService {
    private static final String API_URL = "https://db.ygoprodeck.com/api/v7/";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Random random = new Random();

    public YuGiOhService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Card getRandomCard() {
        try {
            String url = API_URL + "randomcard.php";
            // La API devuelve un objeto Card directamente, no un array
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(jsonResponse, Card.class);
        } catch (Exception e) {
            e.printStackTrace();
            // Si falla, intentar con una carta conocida como fallback
            return getFallbackCard();
        }
    }

    public Card getFallbackCard() {
        Card fallback = new Card();
        fallback.setName("Dark Magician");
        fallback.setType("Spellcaster/Normal");
        fallback.setAtk(2500);
        fallback.setDef(2100);
        fallback.setLevel(7);
        fallback.setRace("Spellcaster");
        fallback.setAttribute("DARK");
        fallback.setDesc("The ultimate wizard in terms of attack and defense.");
        return fallback;
    }
}