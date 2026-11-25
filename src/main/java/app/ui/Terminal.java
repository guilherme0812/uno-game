package app.ui;

import app.model.Card;
import app.model.Color;
import app.model.Player;
import app.model.Type;

import java.util.List;
import java.util.Scanner;

public class Terminal {
    private final Scanner sc = new Scanner(System.in);


    public void showTable(Card cardOnTop, Color actualColor) {
        System.out.println();
        System.out.println("================================");
        System.out.println("Card on table " + this.formatCard(cardOnTop));

        if (cardOnTop.getType() == Type.WILD || cardOnTop.getType() == Type.WILD_DRAW_FOUR) {
            System.out.println("Choosed color: " + this.colorText(actualColor, actualColor.toString()));
        }

        System.out.println("================================");
    }

    public void showHandPlayer(Player player) {
        System.out.println("It's the turn of " + player.getName() + ". There are: ");
        List<Card> hand = player.getCardsInHand();

        for (int i = 0; i < hand.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + this.formatCard(hand.get(i)));
        }

        System.out.println(" [0] draw a card");
    }

    public void notifyWinner(Player player) {
        System.out.println();
        System.out.println("*** " + player.getName() + " winner the game!");
    }

    public int readInt(String question, int min, int max) {
        while (true) {
            System.out.println(question);
            String input = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > min && value < max) return value;
            } catch (NumberFormatException e) {
            }

            System.out.println("The input vakue shuld be a valid value");
            return 1;
        }

    }

    public String readName(int number) {
        while (true) {
            System.out.println("Player " + number + " name");
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) return name;

            System.out.println("The name can not be empty");
        }
    }

    // readChooseCard
    public int readToChooseCard(int cardsOnHand) {
        return this.readInt("Choose a card (0 to buy):", 0, cardsOnHand);
    }

    // readColor
    public Color readColor() {
        System.out.println("Choose a color");
        System.out.println("  [1] Red  [2] Blue  [3] Green  [4] Yellow");
        int op = this.readInt("Color: ", 1, 4);
        return Color.values()[op - 1];
    }

    public boolean confirm(String question) {
        while (true) {
            System.out.print(question + " (y/n): ");
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("y")) return true;
            if (r.equals("n")) return false;
            System.out.println("Response with a 'y' or 'n'.");
        }
    }

    public void message(String message) {
        System.out.println(message);
    }

    public void waitEnter(String message) {
        System.out.println(message);
        sc.nextLine();
    }

    // formatting
    public String formatCard(Card c) {
        String texto;
        switch (c.getType()) {
            case NUMBER:
                texto = c.getColor() + " " + c.getNumber();
                break;
            case SKIP:
                texto = c.getColor() + " SKIP";
                break;
            case REVERSE:
                texto = c.getColor() + " REVERSE";
                break;
            case DRAW_TWO:
                texto = c.getColor() + " +2";
                break;
            case WILD:
                texto = "WILD";
                break;
            default:
                texto = "WILD +4";
                break;
        }
        return this.colorText(c.getColor(), texto);
    }

    public String colorText(Color color, String message) {
        String code;

        switch (color) {
            case RED:
                code = "\u001B[31m";
                break;
            case BLUE:
                code = "\u001B[34m";
                break;
            case GREEN:
                code = "\u001B[32m";
                break;
            case YELLOW:
                code = "\u001B[33m";
                break;
            default:
                code = "\u001B[35m";
                break;
        }

        return code + message + "\u001B[0m";
    }
}
