package com.cebem.medidor.models;

import lombok.Data;
import java.util.List;

@Data
public class Card {
    private int id;
    private String name;
    private String type;
    private String desc;
    private Integer atk;
    private Integer def;
    private Integer level;
    private String race;
    private String attribute;
    private List<CardImage> card_images;
    private List<CardSet> card_sets;
    private List<CardPrice> card_prices;

    @Data
    public static class CardImage {
        private String image_url;
        private String image_url_small;
    }

    @Data
    public static class CardSet {
        private String set_name;
        private String set_code;
    }

    @Data
    public static class CardPrice {
        private String cardmarket_price;
        private String tcgplayer_price;
    }

    public String getImageUrl() {
        if (card_images != null && !card_images.isEmpty()) {
            return card_images.get(0).getImage_url();
        }
        return "https://storage.googleapis.com/ygoprodeck.com/pics/back.jpg";
    }
}