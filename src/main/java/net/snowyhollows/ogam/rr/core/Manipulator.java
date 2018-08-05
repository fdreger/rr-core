package net.snowyhollows.ogam.rr.core;

import java.util.Collections;
import java.util.List;

public interface Manipulator {
    default List<Action> enumeratePossibleActions() {
        return Collections.emptyList();
    };
}
