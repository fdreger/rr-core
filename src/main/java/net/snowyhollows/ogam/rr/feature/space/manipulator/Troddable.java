package net.snowyhollows.ogam.rr.feature.space.manipulator;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Manipulator;

public interface Troddable extends Manipulator {
    public void trodOn(Entity other);
}
