package net.snowyhollows.ogam.rr.feature.combat.component;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Value;

public interface BasicAttributes {
	Value getThac0(Entity other);
	Value getDamage(Entity other);
	Value getAc(Entity other);
	Value getXpBonus(Entity other);
}
