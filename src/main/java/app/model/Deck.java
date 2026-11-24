package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    protected List<Card> deckCards = new ArrayList<>();
    private final Color[] colorList = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};

    public Deck() {
        this.buildDeck();
        this.suffle();

    }

    private void buildDeck() {
        List<Card> enumeratedCards = this.generateEnumeratedCards();
        List<Card> skipCards = this.generateActionCards(Type.SKIP);
        List<Card> reverseCards = this.generateActionCards(Type.REVERSE);
        List<Card> drawTwoCards = this.generateActionCards(Type.DRAW_TWO);
        List<Card> wildCards = this.generateWildCards();

        deckCards.addAll(enumeratedCards);
        deckCards.addAll(skipCards);
        deckCards.addAll(reverseCards);
        deckCards.addAll(drawTwoCards);
        deckCards.addAll(wildCards);

    }

    private List<Card> generateEnumeratedCards() {
        List<Card> cards = new ArrayList<>();
        int[] cardNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i : cardNumbers) {
            for (Color color : colorList) {
                cards.add(new Card(color, Type.NUMBER, i));
                if (i != 0) {
                    cards.add(new Card(color, Type.NUMBER, i));
                }

            }
        }
        return cards;
    }

    private List<Card> generateActionCards(Type type) {
        List<Card> cards = new ArrayList<>();

        for (Color color : colorList) {
            cards.add(new Card(color, type, null));
            cards.add(new Card(color, type, null));
        }
        return cards;
    }

    private List<Card> generateWildCards() {
        List<Card> cards = new ArrayList<>();

        for (Color color : colorList) {
            cards.add(new Card(null, Type.WILD, null));
            cards.add(new Card(null, Type.WILD_DRAW_FOUR, null));
        }
        return cards;
    }

    public void suffle() {
        Collections.shuffle(deckCards);
    }

    public void showDeck() {
        IO.println("deck size: " + deckCards);

        for (Card card : deckCards) {
            Color color = card.getColor();
            Integer number = card.getNumber();
            IO.println("card: " + card.getType() + " " + (color != null ? color : "") + " " + (number != null ? number : ""));
        }
    }

    public Card draw() {
        Card card = this.deckCards.getFirst();
        this.deckCards.removeFirst(0);

        return card;
    }

    public void returnCardToDeck(Card card) {
        this.deckCards.add(card);
    }
}
