package net.snowyhollows.ogam.rr.feature.space.manipulator.impl;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.manipulator.PotentialObstacle;

public class PotentialObstacleImpl implements PotentialObstacle {
    public final boolean isObstacle;

    public PotentialObstacleImpl(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }

    @Override
    public boolean isObstacleFor(Entity other) {
        return false;
    }
}
