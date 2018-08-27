package net.snowyhollows.ogam.rr;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Value;
import net.snowyhollows.ogam.rr.feature.combat.component.BasicAttributes;
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;

public class Player implements BasicAttributes, PotentialObstacle {

    public final FovFow fovFow = new FovFow();

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

    @Override
    public boolean isObstacleFor(Entity other) {
        return true;
    }


}
