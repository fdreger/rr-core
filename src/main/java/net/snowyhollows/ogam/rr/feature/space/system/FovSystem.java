package net.snowyhollows.ogam.rr.feature.space.system;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;

public class FovSystem implements Runnable {

    private final EntityEngine engine;
    private final DisplayListSystem displayListSystem;

    @WithFactory
    public FovSystem(EntityEngine engine, DisplayListSystem displayListSystem) {
        this.engine = engine;
        this.displayListSystem = displayListSystem;
    }

    @Override
    public void run() {
        engine.forEach(Mappers.player, Mappers.position, (e,m) -> {
            e.fovFow.createFrom(m.getCoords().row, m.getCoords().col, 5, 1, c -> {
                for (Entity entity : displayListSystem.displayList) {
                    if (entity.obstacle != null &&
                            entity.position.getCoords().equals(c)
                            && (!entity.obstacle.isObstacleFor(null)
                            || !entity.obstacle.isTemporary())
                            ) {
                        return 0;
                    }
                }
                return 1;
            });
        });

    }
}
