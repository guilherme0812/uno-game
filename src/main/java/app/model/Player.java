package app.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cardsInHand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void addCard(Card card) {
        this.cardsInHand.add(card);
    }

    public void removeCard(Card card) {
        this.cardsInHand.remove(card);
    }

    public boolean withoutCards() {
        return this.cardsInHand.isEmpty();
    }

}
