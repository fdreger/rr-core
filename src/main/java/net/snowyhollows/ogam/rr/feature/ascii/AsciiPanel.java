package net.snowyhollows.ogam.rr.feature.ascii;

import net.snowyhollows.ogam.rr.feature.ascii.component.Color;

public interface AsciiPanel {
    void clear(int row, int col, int width, int height);
    void setDefaultInk(Color ink);
    void setDefaultPaper(Color paper);
    void putChar(int row, int col, char c);
    void putChar(int row, int col, char c, Color ink, Color paper);
    int getWidth();
    int getHeight();

    default void putStr(int row, int col, Color paper, Color ink, CharSequence c) {
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
