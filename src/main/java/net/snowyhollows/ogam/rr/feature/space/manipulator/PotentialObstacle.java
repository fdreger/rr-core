package net.snowyhollows.ogam.rr.feature.space.manipulator;

import net.snowyhollows.ogam.rr.core.Entity;

public interface PotentialObstacle  {
    boolean isObstacleFor(Entity other);
}