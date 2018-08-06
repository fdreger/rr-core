package net.snowyhollows.ogam.rr.feature.ascii.component;

import net.bajobongo.beach.engine.ComponentMapper;

public interface AsciiRepresentation {

    public enum Color {
        RED, YELLOW, CYAN, GREEN, WHITE;
    }
    Color getColor();
    char getChar();
}
