package net.snowyhollows.ogam.rr;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Direction;

public class PlayerSystem {

    private final EntityEngine engine;

    @WithFactory
    public PlayerSystem(EntityEngine entityEngine) {
        this.engine = entityEngine;
    }

    public void run(Main.PlayerCommand command) {
        engine.forEach(Mappers.player, Mappers.position, (e, m) -> {
            if (command == null) {
                return;
            }

            switch (command) {
                case UP: m.move(Direction.N); break;
                case DOWN: m.move(Direction.S); break;
                case LEFT: m.move(Direction.W); break;
                case RIGHT: m.move(Direction.E); break;
            }
        });


    }
}
