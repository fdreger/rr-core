package net.snowyhollows.ogam.rr.feature.space.component.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.component.Movement;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;

public class MovementImpl implements Movement, Serializable {
    private final Space space;
    private Coords position;
    private final Entity me;
    private final Optional optionalThis;

    public MovementImpl(Space space, Entity entity) {
        this.space = space;
        space.addSomethingThatOccupiesSpace(this);
        this.me = entity;
        optionalThis = Optional.of(this.me);
    }

    @Override
    public Optional presentAt(Coords coords) {
        return coords.equals(position) ? optionalThis : Optional.empty();
    }

    @Override
    public boolean move(Direction d) {
        Coords newPosition = d.step(position);

        List<Entity> targetEntities = space.entitiesAt(newPosition, x -> true);

        boolean metObstacle = targetEntities
                .stream()
                .filter(Mappers.obstacle)
                .allMatch(e -> {
                    boolean obstructs = e.obstacle.isObstacleFor(me);
                    if (obstructs && e.bumpable != null) {
                        e.bumpable.bump(me);
                    }
                    return !obstructs;
                });

        if (metObstacle) {
            this.position = newPosition;
            targetEntities.stream().filter(Mappers.treadable).forEach(e -> e.treadable.treadOn(me));
        }

        return metObstacle;
    }

    @Override
    public void setPosition(Coords coords) {
        this.position = coords;
        space.entitiesAt(coords, x -> true)
                .stream()
                .filter(Mappers.treadable)
                .forEach(e -> e.treadable.treadOn(me));
    }
}
