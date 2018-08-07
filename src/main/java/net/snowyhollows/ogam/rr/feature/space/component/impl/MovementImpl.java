package net.snowyhollows.ogam.rr.feature.space.component.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.component.Movement;

public class MovementImpl implements Movement, Serializable {
	private final Space space;
	private Coords position;
    private final Entity me;
    private final Optional<Entity> optionalThis;

    public MovementImpl(Space space, Entity entity) {
        this.space = space;
	    space.addSomethingThatOccupiesSpace(this);
        this.me = entity;
        optionalThis = Optional.of(this.me);
    }

    @Override
    public Optional<Entity> presentAt(Coords coords) {
        return coords.equals(position) ? optionalThis : Optional.empty();
    }

    @Override
    public boolean move(Direction d) {
	    Coords newPosition = d.step(position);

	    List<Entity> targetEntities = space.entitiesAt(newPosition, x -> true);

	    targetEntities.stream().filter(Mappers.bumpable).forEach(e -> {
	    	e.bumpable.bump(me);
	    });

	    boolean movementPossible = targetEntities
			    .stream()
			    .filter(Mappers.obstacle)
			    .allMatch(e -> !e.obstacle.isObstacleFor(me));

		if (movementPossible) {
			this.position = newPosition;
			targetEntities.stream().filter(Mappers.treadable).forEach(e -> e.treadable.treadOn(me));
		}

		return movementPossible;
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
