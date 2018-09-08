package net.snowyhollows.ogam.rr.feature.ascii;

import net.snowyhollows.ogam.rr.feature.ascii.component.Color;

public class DecoratedAsciiWindow implements AsciiPanel {

    private final AsciiPanel parentPanel;

    public DecoratedAsciiWindow(AsciiPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public AsciiPanel createInnerWindow() {
        AsciiWindow aw = new AsciiWindow(this);
        aw.setX(1);
        aw.setY(1);
        aw.setWidth(getWidth() - 2);
        aw.setHeight(getHeight() - 2);
        return aw;
    }

    @Override
    public void clear(int row, int col, int width, int height) {
        parentPanel.clear(row, col, width, height);
    }

    @Override
    public void setDefaultInk(Color ink) {
        parentPanel.setDefaultInk(ink);
    }

    @Override
    public void setDefaultPaper(Color paper) {
        parentPanel.setDefaultPaper(paper);
    }

    @Override
    public void putChar(int row, int col, char c) {
        parentPanel.putChar(row, col, c);
    }

    @Override
    public void putChar(int row, int col, char c, Color ink, Color paper) {
        parentPanel.putChar(row, col, c, ink, paper);

    }

    @Override
    public int getWidth() {
        return parentPanel.getWidth();
    }

    @Override
    public int getHeight() {
        return parentPanel.getHeight();
    }

    public void paint() {
        Color back = Color.GREEN;
        Color fore = Color.BLACK;
        char h =  12 * 16 + 4;
        char v =  11 * 16 + 3;
        char bl = 12 * 16 + 0;
        char br = 13 * 16 + 9;
        char tl = 13 * 16 + 10;
        char tr = 11 * 16 + 15;
        putChar(0, 0, tl, back, fore);
        putChar(0, getWidth() - 1, tr, back, fore);
        putChar(getHeight() - 1, 0, bl, back, fore);
        putChar(getHeight() - 1, getWidth() - 1, br, back, fore);

        for (int row = 1; row < this.getHeight() - 1; row++) {
            putChar(row, 0, v, back, fore);
            putChar(row, getWidth() - 1, v, back, fore);
        }
        for (int col = 1; col < this.getWidth() - 1; col++) {
            putChar(0, col, h, back, fore);
            putChar(getHeight() - 1, col, h, back, fore);
        }
    }
}
