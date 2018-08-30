package net.snowyhollows.ogam.rr.feature.space;

public enum Direction  {
    N(Coords.of(-1, 0)),
    E(Coords.of(0, 1)),
    S(Coords.of(1, 0)),
    W(Coords.of(0, -1)),
	NE(Coords.of(-1, 1)),
	NW(Coords.of(-1, -1)),
	SE(Coords.of(1, 1)),
	SW(Coords.of(1, -1)),
    ZERO(Coords.of(0, 0));

    private final Coords d;

    Direction(Coords d) {
        this.d = d;
    }

    public Coords step(Coords coords) {
        return d.add(coords);
    }

}
