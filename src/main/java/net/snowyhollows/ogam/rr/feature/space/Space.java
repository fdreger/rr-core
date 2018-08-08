package net.snowyhollows.ogam.rr.feature.space;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Space {
	private List<SomethingThatOccupiesSpace> occupants = new ArrayList<>();

    public void addSomethingThatOccupiesSpace(SomethingThatOccupiesSpace somethingThatOccupiesSpace) {
        occupants.add(somethingThatOccupiesSpace);
    }

    public List<Entity> entitiesAt(Coords coords, Predicate<Entity> filter) {
        return occupants.stream()
                .map(s -> s.presentAt(coords))
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()).filter(filter) : Stream.empty())
                .collect(Collectors.toList());
    }

    @WithFactory
	public Space() {
	}

}
