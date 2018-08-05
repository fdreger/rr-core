package net.snowyhollows.ogam.rr.feature.space;

import net.snowyhollows.ogam.rr.core.ActionLabel;

public enum Direction implements ActionLabel {
    N(new Coords(0, -1)),
    E(new Coords(1, 0)),
    S(new Coords(0, 1)),
    W(new Coords(-1, 0));

    private final Coords d;

    Direction(Coords d) {
        this.d = d;
    }

    public Coords step(Coords coords) {
        return d.add(coords);
    }

}
