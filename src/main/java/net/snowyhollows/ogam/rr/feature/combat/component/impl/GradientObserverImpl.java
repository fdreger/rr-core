package net.snowyhollows.ogam.rr.feature.combat.component.impl;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.combat.component.GradientObserver;

public class GradientObserverImpl implements GradientObserver {
    final Entity me;

    public GradientObserverImpl(Entity me) {
        this.me = me;
    }

    @Override
    public void touchedBy(Entity entity) {
        // TODO
    }
}
