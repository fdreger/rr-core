package net.snowyhollows.ogam.rr.feature.combat.component.impl;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.MessageLog;
import net.snowyhollows.ogam.rr.core.Value;
import net.snowyhollows.ogam.rr.feature.combat.component.Attackable;
import net.snowyhollows.ogam.rr.feature.space.component.Bumpable;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;

public class AttackableImpl implements Attackable, Bumpable, PotentialObstacle {
    private final Entity me;
    private final MessageLog messageLog;

    public AttackableImpl(Entity me, MessageLog messageLog) {
        this.me = me;
        this.messageLog = messageLog;
    }

    @Override
    public void isAttackedBy(Entity other) {
        Value.ComparisonResult comparisonResult = me.basicAttributes.damageRoll(other).rollAgainst(other.basicAttributes.attackRoll(me));

        if (comparisonResult == Value.ComparisonResult.LOST) {
            int damage = other.basicAttributes.defenseRoll(me).eval();
            me.destructible.inflict(other, damage);
            System.out.println("inflicted " + damage + " damage");

        }
    }

    @Override
    public void bump(Entity other) {
        me.attackable.isAttackedBy(other);
    }

    @Override
    public boolean isObstacleFor(Entity other) {
        return true;
    }

    @Override
    public boolean isTemporary() {
        return true;
    }
}
