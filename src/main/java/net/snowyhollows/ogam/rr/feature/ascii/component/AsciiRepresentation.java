package net.snowyhollows.ogam.rr.feature.ascii.component;

public interface AsciiRepresentation {

    public enum Color {
        RED, YELLOW, CYAN, GREEN, WHITE;
    }
    Color getColor();
    char getChar();
}
