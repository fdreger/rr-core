package net.snowyhollows.ogam.rr.feature.space.manipulator;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Manipulator;

public interface PotentialObstacle extends Manipulator  {
    public boolean isObstacleFor(Entity other);
}
