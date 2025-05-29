package com.cebem.medidor.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cebem.medidor.models.GiphyResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GiphyService {
    private static final String API_KEY = "wyV9AqIfJXU6CryWrXoU4OLoISFkVGlD";
    private static final String BASE_URL = "https://api.giphy.com/v1/gifs";
    
    private final RestTemplate restTemplate;
    
    public GiphyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public GiphyResponse searchGifs(String query, int limit) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/search")
                .queryParam("api_key", API_KEY)
                .queryParam("q", query)
                .queryParam("limit", limit)
                .queryParam("rating", "g") // Filtro de contenido seguro
                .toUriString();
        
        log.info("Realizando búsqueda en Giphy: {}", url);
        return restTemplate.getForObject(url, GiphyResponse.class);
    }
    
    public GiphyResponse getTrendingGifs(int limit) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/trending")
                .queryParam("api_key", API_KEY)
                .queryParam("limit", limit)
                .queryParam("rating", "g")
                .toUriString();
        
        log.info("Obteniendo GIFs trending de Giphy");
        return restTemplate.getForObject(url, GiphyResponse.class);
    }
}