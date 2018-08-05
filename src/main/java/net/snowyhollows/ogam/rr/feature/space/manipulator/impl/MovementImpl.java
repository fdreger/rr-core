package net.snowyhollows.ogam.rr.feature.space.manipulator.impl;

import net.snowyhollows.ogam.rr.core.Action;
import net.snowyhollows.ogam.rr.core.ActionLabel;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.manipulator.Movement;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MovementImpl implements Movement, Serializable {
    private Coords position;
    private final Entity entity;
    private final Optional<Entity> optionalThis;

    private class MovementAction implements Action {

        private final Direction direction;

        private MovementAction(Direction direction) {
            this.direction = direction;
        }

        @Override
        public ActionLabel getLabel() {
            return direction;
        }

        @Override
        public void perform() {
            move(direction);
        }
    }

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
    public List<Action> enumeratePossibleActions() {
        return Arrays.asList(
                new MovementAction(Direction.N),
                new MovementAction(Direction.S),
                new MovementAction(Direction.E),
                new MovementAction(Direction.W)
        );
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
