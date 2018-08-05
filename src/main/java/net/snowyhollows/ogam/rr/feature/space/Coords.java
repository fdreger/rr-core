package net.snowyhollows.ogam.rr.feature.space;

import java.util.Objects;

public class Coords {

    public final int row;
    public final int col;

    public Coords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coords add(Coords other) {
        return new Coords(row + other.row, other.col);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return row == coords.row &&
                col == coords.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, col);
    }
}
