package net.snowyhollows.ogam.rr.feature.space;

import net.snowyhollows.ogam.rr.core.Entity;

import java.util.Optional;

public interface SomethingThatOccupiesSpace {
    Optional<Entity> presentAt(Coords coords);
}
