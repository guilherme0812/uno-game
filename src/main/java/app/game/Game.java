package app.game;

import app.model.Card;
import app.model.Color;
import app.model.Deck;
import app.model.Player;
import app.ui.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Deck deck = new Deck();
    Color actualColor;
    int direction = 1;
    List<Card> discardPile = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    Terminal terminal;
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
            int cardsQtd = 7;

            for (int cardInt = 0; cardInt < cardsQtd; cardInt++) {
                player.addCard(this.deck.draw());
            }

            IO.println(player.getName() + " have " + player.getCardsInHand().size() + " on hands");
            this.players.add(player);
        }
    }

    public void dealCards() {
    }

    public void turnOverTheTopCard() {

    }

    public boolean playTurn() {
        return true;
    }

}
