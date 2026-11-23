package app.model;

public class Card {
    private final Color color;
    private final Type type;
    private final Integer number;

    public Card(Color color, Type type, Integer number) {
        this.color = color;
        this.type = type;
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public Integer getNumber() {
        return number;
    }

    public Type getType() {
        return type;
    }
}
