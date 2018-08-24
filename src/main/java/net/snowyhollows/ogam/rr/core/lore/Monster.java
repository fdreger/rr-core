package net.snowyhollows.ogam.rr.core.lore;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Value;
import net.snowyhollows.ogam.rr.feature.combat.component.BasicAttributes;

public class Monster implements BasicAttributes {
    private Value attackRoll = Value.ONE;
    private Value defenseRoll = Value.ONE;
    private Value damageRoll = Value.ONE;
    private Entity me;

    public Monster(Entity me) {
        this.me = me;
    }

    @Override
    public Value attackRoll(Entity other) {
        return attackRoll;
    }

    @Override
    public Value defenseRoll(Entity other) {
        return defenseRoll;
    }

    @Override
    public Value damageRoll(Entity other) {
        return damageRoll;
    }

}
