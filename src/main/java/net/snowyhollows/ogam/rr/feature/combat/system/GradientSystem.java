package net.snowyhollows.ogam.rr.feature.combat.system;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;

import java.util.List;

public class GradientSystem  implements Runnable {

    private final EntityEngine engine;
    private final DisplayListSystem displayListSystem;

    @WithFactory
    public GradientSystem(EntityEngine engine, DisplayListSystem displayListSystem) {
        this.engine = engine;
        this.displayListSystem = displayListSystem;
    }

    @Override
    public void run() {
        engine.forEach(Mappers.gradient, Mappers.position, (gradient, position) -> {
            gradient.clear();
            gradient.create(position.getCoords(), 10, c -> {
                Entity entity = displayListSystem.get(c.row, c.col);
                return entity == null || entity.obstacle == null || (!entity.obstacle.isObstacleFor(null) || entity.obstacle.isTemporary());
            });


            engine.forEach(Mappers.gradientObserver, Mappers.position, (gradientObserver, observerPosition) -> {
                if (gradient.get(observerPosition.getCoords()) < Integer.MAX_VALUE) {
                    gradientObserver.touchedBy(gradient);
                }
            });
        });
    }
}
