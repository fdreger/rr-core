package net.snowyhollows.ogam.rr.feature.ascii;

import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;

public interface AsciiPanel {
    void clear(int row, int col, int width, int height);
    void setDefaultInk(AsciiRepresentation.Color ink);
    void setDefaultPaper(AsciiRepresentation.Color paper);
    void putChar(int row, int col, char c);
    void putChar(int row, int col, char c, AsciiRepresentation.Color ink, AsciiRepresentation.Color paper);
    int getWidth();
    int getHeight();

    default void putStr(int row, int col, AsciiRepresentation.Color paper, AsciiRepresentation.Color ink, CharSequence c) {
        for (int i = 0; i < c.length(); i++) {
            setDefaultInk(ink);
            setDefaultPaper(paper);
            putChar(row, col + i, c.charAt(i));
        }
    }

    default void clear() {
        clear(0, 0, getWidth(), getHeight());
    }
}
