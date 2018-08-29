package net.snowyhollows.ogam.rr;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;

public class GwtDisplaySystem {

    private static final int SIZE = 20;
    private final EntityEngine engine;
    private final DisplayListSystem displayListSystem;
    private final Canvas canvas;
    private final Context2d ctx;
    private FovFow fovFow;

    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return canvas.addKeyPressHandler(handler);
    }

    @WithFactory
    public GwtDisplaySystem(EntityEngine engine, DisplayListSystem displayListSystem,@ByName("level.height") int rows,
                            @ByName("level.width") int cols) {
        this.engine = engine;
        this.displayListSystem = displayListSystem;
        canvas = Canvas.createIfSupported();
        canvas.setCoordinateSpaceWidth(cols * SIZE);
        canvas.setCoordinateSpaceHeight(rows * SIZE);
        RootLayoutPanel.get().add(canvas);
        ctx = canvas.getContext2d();
        ctx.setFont("18px monospaced");
        ctx.setTextBaseline(Context2d.TextBaseline.TOP);
        canvas.setTabIndex(0);
        canvas.setFocus(true);
        ctx.translate(0.5f, 0.5f);
    }

    public void run() {
        ctx.setFillStyle("black");
        ctx.fillRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());

        engine.forEach(Mappers.player, p -> {
            fovFow = p.fovFow;
            if (fovFow != null) {
                fovFow.forEachVisible(coords -> {
                    ctx.setFillStyle("grey");
                    ctx.fillRect(coords.col * SIZE, coords.row * SIZE, SIZE, SIZE);
                });
            }
        });

        for (Entity entity : displayListSystem.displayList) {
            if (entity.asciiRepresentation == null || entity.position == null) {
                continue;
            }

            Coords coords = entity.position.getCoords();
            if (
                    (fovFow != null) && (
                            fovFow.isVisible(coords)
                                    || (fovFow.isSeen(coords) && entity.obstacle != null && !entity.obstacle.isTemporary())
                    )) {
                ctx.setFillStyle(entity.asciiRepresentation.getColor().name());
                ctx.fillText("" + entity.asciiRepresentation.getChar(),
                        entity.position.getCoords().col * SIZE,
                        entity.position.getCoords().row * SIZE);
            }
        }
    }
}
