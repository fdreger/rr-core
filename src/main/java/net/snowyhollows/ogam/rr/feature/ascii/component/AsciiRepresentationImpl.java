package net.snowyhollows.ogam.rr.feature.ascii.component;

public class AsciiRepresentationImpl implements AsciiRepresentation {
    private final Color color;
    private final Color backgroundColor;
    private final char _char;

    public AsciiRepresentationImpl(Color color, char _char) {
        this(color, Color.BLACK, _char);
    }

    public AsciiRepresentationImpl(Color color, Color backgroundColor, char _char) {
        this.color = color;
        this.backgroundColor = backgroundColor;
        this._char = _char;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public char getChar() {
        return _char;
    }

}
