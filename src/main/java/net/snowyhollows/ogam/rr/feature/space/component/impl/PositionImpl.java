package net.snowyhollows.ogam.rr.feature.space.component.impl;

import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.component.Position;

import java.util.ArrayList;

public class PositionImpl implements Position {
    private Coords coords = NOWHERE;
	private final EntityEngine engine;
	private final Entity me;

    public PositionImpl(EntityEngine e, Entity entity) {
	    engine = e;
	    this.me = entity;
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
    public void moveTo(Coords coords) {
        setCoords(coords);
        if (coords.equals(NOWHERE)) return;

	    engine.forEachEntity(Mappers.position, e -> {
	    	if (e.position.getCoords().equals(coords) && e.treadable != null) {
	    		e.treadable.treadOn(me);
		    }
	    });
    }

    @Override
    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    @Override
	public Coords getCoords() {
		return coords;
	}
}
