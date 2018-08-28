package net.snowyhollows.ogam.rr.feature.ai.system;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Mappers;

public class ActorSystem implements Runnable {

    private final EntityEngine engine;

    @WithFactory
    public ActorSystem(EntityEngine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        engine.forEach(Mappers.actor, a -> {
            a.act();
        });
    }
}
