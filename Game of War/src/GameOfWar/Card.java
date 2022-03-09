package GameOfWar;

public class Card {

    private Symbol symbol;
    private String value;


    public Card(Symbol symbol, int value) {
        setSymbol(symbol);
        setValue(value);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    /**
     * a method that signs a value to a card according to the given argument
     *
     * @param value
     */
    public void setValue(int value) {
        if (value > 10 || value == 1) {
            switch (value) {
                case 1 -> this.value = "Ace";
                case 13 -> this.value = "King";
                case 12 -> this.value = "Queen";
                case 11 -> this.value = "Jack";
                default -> this.value = "ERROR";
            }
        } else
            this.value = "" + value;
    }

    /**
     * a method that returns the value of a card as an int
     *
     * @return an int representing the value of a card
     */
    public int getNumberOfValue() {
        return switch (this.value) {
            case "Ace" -> 1;
            case "King" -> 13;
            case "Queen" -> 12;
            case "Jack" -> 11;
            default -> Integer.parseInt(this.value);
        };
    }

    @Override
    public String toString() {
        return this.value + " of " + this.symbol;
    }
}
