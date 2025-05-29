package com.cebem.medidor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cebem.medidor.models.Card;
import com.cebem.medidor.services.YuGiOhService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YuGiOhController {
    private final YuGiOhService yuGiOhService;

    @GetMapping("/compare")
    public String compareCards(Model model) {
        Card card1 = getValidMonsterCard();
        Card card2 = getValidMonsterCard();
        
        model.addAttribute("card1", card1);
        model.addAttribute("card2", card2);
        model.addAttribute("winner", compareCardsLogic(card1, card2));
        
        return "comparison2";
    }

    private Card getValidMonsterCard() {
        int attempts = 0;
        while (attempts < 10) {
            Card card = yuGiOhService.getRandomCard();
            if (card.getAtk() != null && card.getDef() != null) {
                return card;
            }
            attempts++;
        }
        return yuGiOhService.getFallbackCard();
    }

    private String compareCardsLogic(Card card1, Card card2) {
        // Primero comparamos por ATK
        if (card1.getAtk() > card2.getAtk()) {
            return card1.getName() + " gana por mayor ATK (" + card1.getAtk() + " vs " + card2.getAtk() + ")";
        } else if (card1.getAtk() < card2.getAtk()) {
            return card2.getName() + " gana por mayor ATK (" + card2.getAtk() + " vs " + card1.getAtk() + ")";
        } else {
            // En caso de empate en ATK, comparamos por DEF
            if (card1.getDef() > card2.getDef()) {
                return card1.getName() + " gana por empate en ATK pero mayor DEF (" + card1.getDef() + " vs " + card2.getDef() + ")";
            } else if (card1.getDef() < card2.getDef()) {
                return card2.getName() + " gana por empate en ATK pero mayor DEF (" + card2.getDef() + " vs " + card1.getDef() + ")";
            } else {
                return "¡Empate total! Ambas cartas tienen ATK=" + card1.getAtk() + " y DEF=" + card1.getDef();
            }
        }
    }
}