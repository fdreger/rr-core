package net.snowyhollows.ogam.rr.feature.combat.component.impl;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.combat.component.Destructible;

public class DestructibleImpl implements Destructible {
    private int hp;

    public DestructibleImpl(int hp) {
        this.hp = hp;
    }

    @Override
    public void inflict(Entity inflicter, int damage) {
        hp -= damage;
    }

    @Override
    public boolean isDestroyed() {
        return hp <= 0;
    }

}
