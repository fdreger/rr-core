package net.snowyhollows.ogam.rr.feature.space.component;

import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.util.ObjectArray2D;

public class DisplayListSystem implements Runnable {

    private final EntityEngine engine;
    public final int fromRow;
    private final int toRow;
    public final int fromCol;
    private final int toCol;
    private final ObjectArray2D<Entity> coordsIndex = new ObjectArray2D<>(100, 100, null, null);

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
        coordsIndex.fillWith(null);
        engine.forEachEntity(Mappers.position, e -> {
            Coords coords = e.position.getCoords();
            int row = coords.row;
            int col = coords.col;
            coordsIndex.put(row, col, e);
        });
    }

    public void visit(ObjectArray2D.Visitor<Entity> visitor) {
        coordsIndex.visit(visitor);
    }

    public Entity get(int row, int col) {
        return coordsIndex.get(row, col);
    }
}
