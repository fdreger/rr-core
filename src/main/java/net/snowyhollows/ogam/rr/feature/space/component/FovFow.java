package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.util.ObjectArray2D;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FovFow {

    public float getVisible(Coords coords) {
        return data.get(coords.row, coords.col).visible;
    }

    public boolean isSeen(Coords coords) {
        return data.get(coords.row, coords.col).seen;
    }

    public interface Transparency {
        float transparency(Coords coords);
    }

    private static class SquareData {
        public float visible;
        public boolean seen;
        public float transparency;
        public Coords coords;

        public SquareData(Coords coords) {
            this.coords = coords;
        }
    }

    private ObjectArray2D<SquareData> data = new ObjectArray2D<>(100, 100,
            new SquareData(Coords.NOWHERE),
            new SquareData(Coords.NOWHERE), (row, col, x) -> new SquareData(Coords.of(row, col))
    );

    public void forEachVisible(BiConsumer<Coords, Float> consumer) {
        data.visit((row, col, t) -> {
            if (t.visible > 0) {
                consumer.accept(t.coords, t.visible);
            }
        });
    }

    public void createFrom(int row, int col, int radius, float light, Transparency transparency) {
        data.visit((q, w, t) -> {
            t.visible = 0;
            t.transparency = transparency.transparency(t.coords);
        });

        data.get(row, col).visible = 1;
        data.get(row, col).seen = true;

        for (int dr = -radius; dr <= radius; dr++) {
            for (int dc = -radius; dc <= radius; dc++) {
                drawLine(row, col, row + dr, col + dc, light);
            }
        }
    }

    public void clearSeen() {
        data.visit((q, w, t) -> {
            t.visible = 0;
            t.seen = false;
        });
    }

    private void drawLine(int row1, int col1, int row2, int col2, float light) {
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

            SquareData squareData = data.get(Math.round(row), Math.round(col));
            squareData.seen = true;
            squareData.visible = light;
            light *= squareData.transparency;

            if (light < 0.1f) {
                break;
            }
        }
    }
}
