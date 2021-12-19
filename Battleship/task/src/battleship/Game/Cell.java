package battleship.Game;

public enum Cell {
    SHIP('O'), FOG('~'), HIT('X'), MISS('M');

    private char symbol;

    Cell(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
