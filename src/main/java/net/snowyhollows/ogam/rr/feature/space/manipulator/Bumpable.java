package net.snowyhollows.ogam.rr.feature.space.manipulator;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Manipulator;

public interface Bumpable extends Manipulator {
    public void bump(Entity other);
}
