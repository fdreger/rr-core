package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;

public interface Position {
    Coords NOWHERE = new Coords(Integer.MIN_VALUE, Integer.MIN_VALUE);

    boolean move(Direction d);
    void setCoords(Coords coords);
    Coords getCoords();
}
