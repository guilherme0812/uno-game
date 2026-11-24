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
        boolean end = false;

        while (!end) {
            end = playTurn();
        }
    }

    public void settingPlayers() {
    }

    public void dealCards() {
    }

    public void turnOverTheTopCard() {

    }

    public boolean playTurn() {
        return true;
    }

}
