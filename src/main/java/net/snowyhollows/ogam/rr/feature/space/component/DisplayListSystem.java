package net.snowyhollows.ogam.rr.feature.space.component;

import com.googlecode.lanterna.screen.Screen;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.ScreenFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;

import java.util.ArrayList;
import java.util.List;

public class DisplayListSystem implements Runnable {

    private final EntityEngine engine;
    public final int fromRow;
    private final int toRow;
    public final int fromCol;
    private final int toCol;
    public final List<Entity> displayList = new ArrayList<>();

    @WithFactory
    public DisplayListSystem(EntityEngine engine,
                             @ByName("level.height") int rows,
                             @ByName("level.width") int cols) {
        this.engine = engine;
        fromRow = 0;
        toRow = fromRow + rows;
        fromCol = 0;
        toCol = fromCol + cols;
    }

    @Override
    public void run() {
        engine.forEachEntity(Mappers.position, e -> {
            Coords coords = e.position.getCoords();
            int row = coords.row;
            int col = coords.col;
            if (row >= fromRow && row <= toRow && col >= fromCol && col <= toCol) {
                displayList.add(e);
            }
        });


    }
}
