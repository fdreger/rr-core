package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.ogam.rr.core.Entity;

public interface PotentialObstacle  {
    boolean isObstacleFor(Entity other);
    default boolean isTemporary() {
        return false;
    }
}
