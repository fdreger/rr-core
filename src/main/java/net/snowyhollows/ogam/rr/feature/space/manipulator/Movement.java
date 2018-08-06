package net.snowyhollows.ogam.rr.feature.space.manipulator;

import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.SomethingThatOccupiesSpace;

public interface Movement extends SomethingThatOccupiesSpace {
    public void move(Direction d);
    public void setPosition(Coords coords);
}
