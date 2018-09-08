package net.snowyhollows.ogam.rr.feature.ascii;

import net.snowyhollows.ogam.rr.feature.ascii.component.Color;

public class AsciiWindow implements AsciiPanel {
    protected final AsciiPanel parentPanel;

    private Color paper = Color.BLACK;
    private Color ink = Color.WHITE;
    private int width;
    private int height;
    private int x;
    private int y;

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private boolean isValid(int row, int col) {
        return col >= 0 && col < width && row >= 0 && row < height;
    }

    public AsciiWindow(AsciiPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    @Override
    public void clear(int row, int col, int width, int height) {
        int clippedRow = Math.min(row, getHeight() - 1);
        int clippedCol = Math.min(col, getWidth() - 1);

        int clippedWidth = Math.min(getWidth() - clippedCol, width);
        int clippedHeight = Math.min(getHeight() - clippedRow, height);

        parentPanel.clear(clippedRow + y, clippedCol + x, clippedWidth, clippedHeight);
    }

    @Override
    public void setDefaultInk(Color ink) {
        this.ink = ink;
    }

    @Override
    public void setDefaultPaper(Color paper) {
        this.paper = paper;
    }

    @Override
    public void putChar(int row, int col, char c) {
        putChar(row + y, col + x, c, this.paper, this.ink);
    }

    @Override
    public void putChar(int row, int col, char c, Color ink, Color paper) {
        if (!isValid(row, col)) {
            return;
        }
        parentPanel.putChar(row + y, col + x, c, paper, ink);
    }

}
