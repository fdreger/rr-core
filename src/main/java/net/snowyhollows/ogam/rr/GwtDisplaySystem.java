package net.snowyhollows.ogam.rr;

import elemental2.dom.*;
import elemental2.dom.CanvasRenderingContext2D.FillStyleUnionType;
import elemental2.promise.Promise;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;

import java.util.function.Consumer;

public class GwtDisplaySystem {

    private static final int SIZE = 10;
    private final EntityEngine engine;
    private final DisplayListSystem displayListSystem;
    private FovFow fovFow;
    private HTMLImageElement font16x16;
    private HTMLCanvasElement main;
    private HTMLCanvasElement background;
    private HTMLCanvasElement foreground;
    private CanvasRenderingContext2D backgroundCtx;
    private CanvasRenderingContext2D foregroundCtx;
    private CanvasRenderingContext2D mainCtx;
    private final Document document = DomGlobal.document;

    public Promise initiated;
    public Consumer<String> keyboardListener;

    @WithFactory
    public GwtDisplaySystem(EntityEngine engine, DisplayListSystem displayListSystem,@ByName("level.height") int rows,
                            @ByName("level.width") int cols) {
        this.engine = engine;
        this.displayListSystem = displayListSystem;
        font16x16 = (HTMLImageElement) document.createElement("img");
        font16x16 .src = "cp437_10x10.png";
        initiated = new Promise<>(((resolve, reject) -> {
            font16x16 .addEventListener("load", e -> resolve.onInvoke(font16x16) );
            font16x16 .addEventListener("error", e -> reject.onInvoke(e));
        }));

        main = createCanvas(rows, cols);
        mainCtx = getContext2D(main);
        mainCtx.setTextBaseline("hanging");

        foreground = createCanvas(1, 1);
        foregroundCtx = getContext2D(foreground);

        background = createCanvas(1, 1);
        backgroundCtx = getContext2D(background);

        main.addEventListener("keydown", e -> {
            if (keyboardListener != null) {
                keyboardListener.accept(((KeyboardEvent) e).key);
            }
        });

        document.querySelector("body").appendChild(main);

        main.tabIndex = 0;
        main.focus();
    }

    private static CanvasRenderingContext2D getContext2D(HTMLCanvasElement main) {
        return (CanvasRenderingContext2D) (Object) main.getContext("2d");
    }

    private HTMLCanvasElement createCanvas(int rows, int cols) {
        HTMLCanvasElement canvas = (HTMLCanvasElement) document.createElement("canvas");
        canvas.width = cols * SIZE;
        canvas.height = rows * SIZE;
        return canvas;
    }

    public void run() {
        mainCtx.fillStyle = FillStyleUnionType.of("black");
        mainCtx.fillRect(0, 0, main.width, main.height);

        engine.forEach(Mappers.player, p -> {
            fovFow = p.fovFow;
            if (fovFow != null) {
                fovFow.forEachVisible(coords -> {
                    mainCtx.fillStyle = FillStyleUnionType.of("grey");
                    mainCtx.fillRect(coords.col * SIZE, coords.row * SIZE, SIZE, SIZE);
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
                putChar(entity.position.getCoords().row , entity.position.getCoords().col, entity.asciiRepresentation.getBackgroundColor().name(), entity.asciiRepresentation.getColor().name(), entity.asciiRepresentation.getChar());
            }
        });
    }

    private void putChar(int row, int col, String backgroundColor, String foregroundColor, char c) {
        int x = (short)c % 16;
        int y = (short)c / 16;

        foregroundCtx.globalCompositeOperation = "source-over";
        foregroundCtx.fillStyle = FillStyleUnionType.of(foregroundColor);
        foregroundCtx.fillRect(0, 0, SIZE, SIZE);
        backgroundCtx.fillStyle = FillStyleUnionType.of(backgroundColor);
        backgroundCtx.fillRect(0, 0, SIZE, SIZE);

        foregroundCtx.globalCompositeOperation = "destination-in";
        foregroundCtx.drawImage(font16x16, x * SIZE, y * SIZE, SIZE, SIZE, 0, 0, SIZE, SIZE);
        backgroundCtx.drawImage(foreground, 0, 0);
        mainCtx.drawImage(background, col * SIZE, row * SIZE);

//        mainCtx.drawImage(font16x16, x * SIZE, y * SIZE, SIZE, SIZE, col * SIZE, row * SIZE, SIZE, SIZE);
    }
}
