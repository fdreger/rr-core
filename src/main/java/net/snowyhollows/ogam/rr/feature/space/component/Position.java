package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;

public interface Position {
    Coords NOWHERE = Coords.NOWHERE;

    boolean move(Direction d);
    void moveTo(Coords coords);
    void setCoords(Coords coords);
    Coords getCoords();
}
