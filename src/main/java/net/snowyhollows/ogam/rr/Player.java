package net.snowyhollows.ogam.rr;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Value;
import net.snowyhollows.ogam.rr.feature.combat.component.BasicAttributes;

public class Player implements BasicAttributes {
    @Override
    public Value attackRoll(Entity other) {
        return new Value(Value.RANDOM, 8, 1);
    }

    @Override
    public Value defenseRoll(Entity other) {
        return new Value(Value.RANDOM, 8, 1);
    }

    @Override
    public Value damageRoll(Entity other) {
        return new Value(Value.RANDOM, 8, 1);
    }
}
