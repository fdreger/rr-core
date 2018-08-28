package net.snowyhollows.ogam.rr.feature.combat.system;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Mappers;

public class DestructibleSystem implements Runnable {

    private final EntityEngine engine;

    @WithFactory
    public DestructibleSystem(EntityEngine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        engine.forEach(Mappers.destructible, d -> {
            if (d.isDestroyed()) {
                System.out.println(engine.currentEntity() + " is destroyed");
                engine.removeCurrentEntity();
            }
        });
    }
}
