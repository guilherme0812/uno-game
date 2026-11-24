package app.game;

import app.model.*;
import app.ui.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Deck deck = new Deck();
    List<Card> discardPile = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    Terminal terminal;

    Color actualColor;
    int direction = 1;
    int actual = 0;

    public Game(Terminal terminal) {
        this.terminal = terminal;
    }

    public void start() {
        this.settingPlayers();
        this.dealCards();
        this.turnOverTheTopCard();

        boolean end = false;

        while (!end) {
            end = playTurn();
        }

        IO.println(deck.getSize());
    }

    public void settingPlayers() {
        int qtd = 2;
        for (int i = 0; i < qtd; i++) {
            Player player = new Player("Player " + i);
            this.players.add(player);
        }
    }

    public void dealCards() {
        for (Player player : players) {
            int cardsQtd = 7;

            for (int cardInt = 0; cardInt < cardsQtd; cardInt++) {
                player.addCard(this.deck.draw());
            }

            IO.println(player.getName() + " have " + player.getCardsInHand().size() + " on hands");
        }
    }

    public void turnOverTheTopCard() {
        Card card = this.deck.draw();
        while (card.getType() != Type.NUMBER) {
            this.deck.returnCardToDeck(card);

            card = this.deck.draw();
        }
        this.actualColor = card.getColor();
        this.discardPile.add(card);
    }

    public boolean playTurn() {
        Player player = players.get(this.actual);
        Card lastCardInPile = this.discardPile.getLast();
        int initialCardOnHandsQtd = player.getCardsInHand().size();

        IO.println("Deck size: " + deck.getSize() + " " + "discard pile size " + discardPile.size());
        IO.println("last card in : " + lastCardInPile.getColor() + " " + (lastCardInPile.getNumber() != null ? lastCardInPile.getNumber() : "") + " . Actual color: " + actualColor);

        IO.println("--------------- " + player.getName() + " --------------- ");

        for (Card card : player.getCardsInHand()) {
            Integer cardNumber = card.getNumber();
            Color cardColor = card.getColor();
            String cardLabel = (cardNumber != null ? cardNumber.toString() : card.getType()) + " " + (cardColor != null ? cardColor : "");

            IO.println(this.terminal.colorText(cardColor, "++ CARD " + cardLabel));
        }

        for (Card card : player.getCardsInHand()) {
            Integer cardNumber = card.getNumber();
            Color cardColor = card.getColor();
            String cardLabel = (cardNumber != null ? cardNumber.toString() : card.getType()) + " " + (cardColor != null ? cardColor : "");

            if (cardColor != null && (cardColor == this.actualColor)) {
                IO.println("played a card : " + cardLabel);

                this.discardPile.add(card);
                player.removeCard(card);
                this.actualColor = cardColor;
                break;
            } else if (cardNumber != null && (cardNumber == lastCardInPile.getNumber())) {
                IO.println("played a card : " + cardLabel);

                this.discardPile.add(card);
                player.removeCard(card);
                this.actualColor = card.getColor();
                break;
            } else if (card.getType() == Type.WILD || card.getType() == Type.WILD_DRAW_FOUR) {
                IO.println("played a card : " + cardLabel);
                this.discardPile.add(card);
                player.removeCard(card);

                break;
            }


        }

        if (initialCardOnHandsQtd == player.getCardsInHand().size()) {
            IO.println("PLayer should draw a new card");
            player.addCard(deck.draw());
        }

        IO.println("------------------------------ ");

        IO.println("TESTE Deck size: " + deck.getSize() + " " + "discard pile size " + discardPile.size());

        if (player.withoutCards()) {
            IO.println("End game, " + player.getName() + " is winner!");
            return true;
        } else {
            this.actual = this.actual == 0 ? 1 : 0;
            return false;
        }


    }

}
