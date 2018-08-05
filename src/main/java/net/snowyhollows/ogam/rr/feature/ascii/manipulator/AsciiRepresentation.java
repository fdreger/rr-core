package net.snowyhollows.ogam.rr.feature.ascii.manipulator;

import net.snowyhollows.ogam.rr.core.Manipulator;

public interface AsciiRepresentation extends Manipulator {
    public enum Color {
        RED, YELLOW, CYAN, GREEN, WHITE;
    }
    Color getColor();
    char getChar();
}
