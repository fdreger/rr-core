package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.ogam.rr.feature.space.Coords;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class FovFow {

    public boolean isVisible(Coords coords) {
        return visible.contains(coords);
    }

    public boolean isSeen(Coords coords) {
        return seen.contains(coords);
    }

    public interface Transparency {
        float transparency(Coords coords);
    }

    Set<Coords> visible = new HashSet<>();
    Set<Coords> seen = new HashSet<>();

    public void forEachVisible(Consumer<Coords> consumer) {
        visible.forEach(consumer);
    }


    public void createFrom(int row, int col, int radius, float light, Transparency transparency) {
        visible.clear();
        mark(new Coords(row, col), light);

        for (int dr = -radius; dr <= radius; dr++) {
            for (int dc = -radius; dc <= radius; dc++) {
                drawLine(row, col, row + dr, col + dc, transparency, light);
            }
        }
    }

    public void clearSeen() {
        seen.clear();
        visible.clear();
    }

    private void drawLine(int row1, int col1, int row2, int col2, Transparency brush, float light) {
        int dr = row2 - row1;
        int dc = col2 - col1;
        float step = 0;

        if (Math.abs(dr) >= Math.abs(dc)) {
            step = Math.abs(dr);
        } else {
            step = Math.abs(dc);
        }

        float stepRow = dr / step;
        float stepCol = dc / step;

        float row = row1;
        float col = col1;

        for (int i = 0; i <= step; i++) {
            row += stepRow;
            col += stepCol;

            Coords coords = new Coords(Math.round(row), Math.round(col));
            mark(coords, light);
            light *= brush.transparency(coords);

            if (light < 0.1f) {
                break;
            }
        }

    }

    private void mark(Coords coords, float light) {
        seen.add(coords);
        visible.add(coords);
    }

}
