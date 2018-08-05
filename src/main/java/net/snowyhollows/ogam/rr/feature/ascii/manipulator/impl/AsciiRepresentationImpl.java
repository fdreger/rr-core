package net.snowyhollows.ogam.rr.feature.ascii.manipulator.impl;

import net.snowyhollows.ogam.rr.feature.ascii.manipulator.AsciiRepresentation;

public class AsciiRepresentationImpl implements AsciiRepresentation {
    private final AsciiRepresentation.Color color;
    private final char _char;

    public AsciiRepresentationImpl(AsciiRepresentation.Color color, char _char) {
        this.color = color;
        this._char = _char;
    }

    public AsciiRepresentation.Color getColor() {
        return color;
    }

    @Override
    public char getChar() {
        return _char;
    }

}
