package net.snowyhollows.ogam.rr.feature.combat.component;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Value;

public interface BasicAttributes {
	Value attackRoll(Entity other);
	Value defenseRoll(Entity other);
	Value damageRoll(Entity other);
}
