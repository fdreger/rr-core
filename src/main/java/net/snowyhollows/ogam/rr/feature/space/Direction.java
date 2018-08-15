package net.snowyhollows.ogam.rr.feature.space;

public enum Direction  {
    N(new Coords(-1, 0)),
    E(new Coords(0, 1)),
    S(new Coords(1, 0)),
    W(new Coords(0, -1)),
	NE(new Coords(-1, 1)),
	NW(new Coords(-1, -1)),
	SE(new Coords(1, 1)),
	SW(new Coords(1, -1));

    private final Coords d;

    Direction(Coords d) {
        this.d = d;
    }

    public Coords step(Coords coords) {
        return d.add(coords);
    }

}
