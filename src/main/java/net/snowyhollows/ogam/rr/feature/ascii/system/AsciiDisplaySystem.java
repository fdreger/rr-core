package net.snowyhollows.ogam.rr.feature.ascii.system;

import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.feature.ascii.AsciiPanel;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;

public class AsciiDisplaySystem {
    private final EntityEngine engine;
    private final DisplayListSystem displayListSystem;
    private FovFow fovFow;
    private AsciiPanel asciiPanel;

    @WithFactory
    public AsciiDisplaySystem(EntityEngine engine, DisplayListSystem displayListSystem, @ByName("level.height") int rows,
                              @ByName("level.width") int cols, @ByName("asciiPanel") AsciiPanel asciiPanel) {
        this.engine = engine;
        this.displayListSystem = displayListSystem;
        this.asciiPanel = asciiPanel;
    }

    public void run() {

        asciiPanel.clear();

        engine.forEach(Mappers.player, p -> {
            fovFow = p.fovFow;
            if (fovFow != null) {
                fovFow.forEachVisible(coords -> {
                    asciiPanel.putChar(coords.row, coords.col, ' ', AsciiRepresentation.Color.GREY, AsciiRepresentation.Color.BLACK);
                });
            }
        });

        displayListSystem.visit((row, col, entity) -> {
            if (entity == null || entity.asciiRepresentation == null || entity.position == null) {
                return;
            }

            Coords coords = entity.position.getCoords();
            if (
                    (fovFow != null) && (
                            fovFow.isVisible(coords)
                                    || (fovFow.isSeen(coords) && entity.obstacle != null && !entity.obstacle.isTemporary())
                    )) {
                asciiPanel.putChar(entity.position.getCoords().row , entity.position.getCoords().col, entity.asciiRepresentation.getChar(), entity.asciiRepresentation.getBackgroundColor(), entity.asciiRepresentation.getColor());
            }
        });
    }

}
