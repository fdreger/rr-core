package net.snowyhollows.ogam.rr.feature.space.manipulator.impl;

import java.io.Serializable;
import java.util.Optional;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.manipulator.Movement;

public class MovementImpl implements Movement, Serializable {
    private Coords position;
    private final Entity entity;
    private final Optional<Entity> optionalThis;

    public MovementImpl(Space space, Entity entity) {
        space.addSomethingThatOccupiesSpace(this);
        this.entity= entity;
        optionalThis = Optional.of(this.entity);
    }

    @Override
    public Optional<Entity> presentAt(Coords coords) {
        return coords.equals(position) ? optionalThis : Optional.empty();
    }

    @Override
    public void move(Direction d) {
        position = d.step(position);
    }

    @Override
    public void setPosition(Coords coords) {
        this.position = coords;
    }
}
