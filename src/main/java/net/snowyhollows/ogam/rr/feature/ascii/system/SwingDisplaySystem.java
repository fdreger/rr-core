package net.snowyhollows.ogam.rr.feature.ascii.system;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.ScreenFactory;
import net.snowyhollows.ogam.rr.Util;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;

import java.io.IOException;

public class SwingDisplaySystem implements Runnable {

    private final EntityEngine engine;
    private final Screen screen;
    private final DisplayListSystem displayListSystem;

    @WithFactory
    public SwingDisplaySystem(EntityEngine engine, @ByFactory(ScreenFactory.class) Screen screen, DisplayListSystem displayListSystem) {
        this.engine = engine;
        this.screen = screen;
        this.displayListSystem = displayListSystem;
    }

    FovFow fovFow = null;

    @Override
    public void run() {
        engine.forEach(Mappers.player, p -> {
            fovFow = p.fovFow;
            if (fovFow != null) {
                fovFow.forEachVisible((coords, light )-> {
                    TextCharacter tc = new TextCharacter(
                            '.',
                            TextColor.ANSI.WHITE,
                            TextColor.ANSI.BLACK);
                    screen.setCharacter(coords.col - displayListSystem.fromCol, coords.row - displayListSystem.fromRow, tc);
                });
            }
        });

        displayListSystem.visit( (row, col, entity) -> {
            if (entity == null || entity.asciiRepresentation == null) {
                return;
            }

            Coords coords = entity.position.getCoords();
            if (
                    (fovFow != null) && (
                            fovFow.getVisible(coords) > 0
                                    || (fovFow.isSeen(coords) && entity.obstacle != null && !entity.obstacle.isTemporary())
                    )) {
                TextCharacter tc = new TextCharacter(
                        entity.asciiRepresentation.getChar(),
                        Util.colors.get(entity.asciiRepresentation.getColor()),
                        TextColor.ANSI.BLACK);
                screen.setCharacter(coords.col - displayListSystem.fromCol, coords.row - displayListSystem.fromRow, tc);
            }
        });

        try {
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
