package jo.melanie.advent.twentyfive.puzzle.six;

public enum Operator {
    ADD("+"),
    MULTIPLY("*");

    public final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public static Operator valueOfChar(char symbol) {
        return valueOfSymbol(String.valueOf(symbol));
    }

    public static Operator valueOfSymbol(String symbol) {
        for (Operator e : values()) {
            if (java.util.Objects.equals(e.symbol, symbol)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No operator for " + symbol);
    }

}
