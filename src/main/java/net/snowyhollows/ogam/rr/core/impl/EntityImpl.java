package net.snowyhollows.ogam.rr.core.impl;

import net.snowyhollows.ogam.rr.core.Action;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Manipulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EntityImpl implements Entity {

    private List<Manipulator> manipulators = new ArrayList<>();

    public void addManipulator(Manipulator manipulator) {
        manipulators.add(manipulator);
    }

    @Override
    public List<Action> enumeratePossibleActions() {
        return manipulators.stream().flatMap(m -> m.enumeratePossibleActions().stream()).collect(Collectors.toList());
    }

    @Override
    public <T extends Manipulator> Optional<T> manipulate(Class<T> manipulatorClass) {
        return manipulators.stream().filter(m -> manipulatorClass.isInstance(m)).findFirst().map(x -> (T)x);
    }
}
