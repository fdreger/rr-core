package net.snowyhollows.ogam.rr.core.lore;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Value;
import net.snowyhollows.ogam.rr.feature.ai.component.Actor;
import net.snowyhollows.ogam.rr.feature.combat.component.BasicAttributes;
import net.snowyhollows.ogam.rr.feature.combat.component.GradientObserver;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.Gradient;

public class Monster implements BasicAttributes, GradientObserver, Actor {
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

    @Override
    public void act() {
        if (gradient != null) {
            Direction where = gradient.follow(me.position.getCoords());
            me.position.move(where);
        }
        gradient = null;
    }

    private Gradient gradient;

    @Override
    public void touchedBy(Gradient gradient) {
        this.gradient = gradient;
    }
}
