package app.ui;

import app.model.Card;
import app.model.Color;

import java.util.Scanner;

public class Terminal {
    private final Scanner sc = new Scanner();

    public void message(String message) {
        System.out.println(message);
    }

    public void waitEnter(String message) {
        System.out.println(message);
        sc.nextLine();
    }

    // formatting
    public String formatCard(Card card) {
        return "";
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
