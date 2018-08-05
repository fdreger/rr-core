package net.snowyhollows.ogam.rr.core;

import java.util.List;
import java.util.Optional;

public interface Entity {
    List<Action> enumeratePossibleActions();

    <T extends Manipulator> Optional<T> manipulate(Class<T> manipulatorClass);
}
