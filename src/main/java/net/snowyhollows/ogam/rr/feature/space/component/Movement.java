package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.SomethingThatOccupiesSpace;

public interface Movement extends SomethingThatOccupiesSpace {
    boolean move(Direction d);
    void setPosition(Coords coords);
}
