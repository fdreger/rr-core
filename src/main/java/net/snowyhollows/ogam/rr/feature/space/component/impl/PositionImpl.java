package net.snowyhollows.ogam.rr.feature.space.component.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.component.Position;

public class PositionImpl implements Position, Serializable {
    private Coords coords;
	private final EntityEngine engine;
	private final Entity me;
    private final Optional optionalThis;

    public PositionImpl(EntityEngine e, Entity entity) {
	    engine = e;
	    this.me = entity;
        optionalThis = Optional.of(this.me);
    }

    @Override
    public Optional presentAt(Coords coords) {
        return coords.equals(this.coords) ? optionalThis : Optional.empty();
    }

    private static ArrayList<Entity> tmpTargetEntities = new ArrayList<>();

    @Override
    public boolean move(Direction d) {
        Coords newPosition = d.step(coords);

        tmpTargetEntities.clear();

        engine.forEachEntity(Mappers.position, e -> {
        	if (e.position.getCoords().equals(newPosition)) {
		        tmpTargetEntities.add(e);
	        }
        });

        boolean metObstacle = tmpTargetEntities
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
            this.coords = newPosition;
            tmpTargetEntities.stream().filter(Mappers.treadable).forEach(e -> e.treadable.treadOn(me));
        }

        return metObstacle;
    }

    @Override
    public void setCoords(Coords coords) {
        this.coords = coords;
	    engine.forEachEntity(Mappers.position, e -> {
	    	if (e.position.getCoords().equals(coords) && e.treadable != null) {
	    		e.treadable.treadOn(me);
		    }
	    });
    }

	@Override
	public Coords getCoords() {
		return coords;
	}
}
