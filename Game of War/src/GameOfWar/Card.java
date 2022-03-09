package GameOfWar;

public class Card {

    private Symbol symbol;
    private String value;

    public Card(Symbol symbol, String value) {
        setSymbol(symbol);
        setValue(value);
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(int value) {
        if (value > 10 || value == 1) {
            switch (value) {
                case 1:
                    this.value = "Ace";
                    break;
                case 13:
                    this.value = "King";
                    break;
                case 12:
                    this.value = "Queen";
                    break;
                case 11:
                    this.value = "Jack";
                    break;
                default:
                    this.value = "ERROR";
            }
        } else
            this.value = "" + value;
    }

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
