package net.snowyhollows.ogam.rr.feature.space;

import java.util.Objects;

public class Coords {

    public static final Coords NOWHERE = new Coords(Integer.MIN_VALUE, Integer.MIN_VALUE);

    public final int row;
    public final int col;

    public static Coords of(int row, int col) {
        return new Coords(row, col);
    }

    private Coords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coords add(Coords other) {
        return Coords.of(row + other.row, col + other.col);
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
