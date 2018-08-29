package net.snowyhollows.ogam.rr.feature.ascii.component;

public interface AsciiRepresentation {

    enum Color {
        RED, YELLOW, CYAN, GREEN, WHITE, BLACK
    }
    Color getColor();
    Color getBackgroundColor();
    char getChar();
}
