package net.snowyhollows.ogam.rr.feature.combat.component;

import net.snowyhollows.ogam.rr.core.Entity;

public interface Destructible {
	void inflict(Entity inflicter, int hp);
	boolean isDestroyed();
}
