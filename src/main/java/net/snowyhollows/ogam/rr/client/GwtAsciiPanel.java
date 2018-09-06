package net.snowyhollows.ogam.rr.client;

import elemental2.dom.*;
import elemental2.promise.Promise;
import net.snowyhollows.ogam.rr.feature.ascii.AsciiPanel;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;

import java.util.function.Consumer;

public class GwtAsciiPanel implements AsciiPanel {
    private final int cols;
    private final int rows;
    private final int size;
    private AsciiRepresentation.Color backgroundColor;
    private HTMLImageElement font16x16;
    private HTMLCanvasElement main;
    private HTMLCanvasElement background;
    private HTMLCanvasElement foreground;
    private CanvasRenderingContext2D backgroundCtx;
    private CanvasRenderingContext2D foregroundCtx;
    private CanvasRenderingContext2D mainCtx;
    private final Document document = DomGlobal.document;
    private Promise initiated;
    private Consumer<String> keyboardListener;

    private AsciiRepresentation.Color ink;
    private AsciiRepresentation.Color paper;

    public Promise getInitiated() {
        return initiated;
    }

    public void setKeyboardListener(Consumer<String> keyboardListener) {
        this.keyboardListener = keyboardListener;
    }

    public GwtAsciiPanel(int cols, int rows, int size, AsciiRepresentation.Color backgroundColor) {
        this.cols = cols;
        this.rows = rows;
        this.size = size;
        this.backgroundColor = backgroundColor;

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
            e.preventDefault();
        });

        document.querySelector("body").appendChild(main);

        main.tabIndex = 0;
        main.focus();

    }


    @Override
    public void clear(int row, int col, int width, int height) {
        mainCtx.fillStyle = CanvasRenderingContext2D.FillStyleUnionType.of(backgroundColor.name());
        mainCtx.fillRect(col * size, row * size, width * size, height * size);
    }

    @Override
    public void setDefaultInk(AsciiRepresentation.Color ink) {
        this.ink = ink;
    }

    @Override
    public void setDefaultPaper(AsciiRepresentation.Color paper) {
        this.paper = paper;
    }

    @Override
    public void putChar(int row, int col, char c) {
        putChar(row, col, c, paper, ink);

    }

    @Override
    public void putChar(int row, int col, char c, AsciiRepresentation.Color backgroundColor, AsciiRepresentation.Color foregroundColor) {
        int x = (short)c % 16;
        int y = (short)c / 16;

        foregroundCtx.globalCompositeOperation = "source-over";
        foregroundCtx.fillStyle = CanvasRenderingContext2D.FillStyleUnionType.of(foregroundColor.name());
        foregroundCtx.fillRect(0, 0, size, size);
        backgroundCtx.fillStyle = CanvasRenderingContext2D.FillStyleUnionType.of(backgroundColor.name());
        backgroundCtx.fillRect(0, 0, size, size);

        foregroundCtx.globalCompositeOperation = "destination-in";
        foregroundCtx.drawImage(font16x16, x * size, y * size, size, size, 0, 0, size, size);
        backgroundCtx.drawImage(foreground, 0, 0);
        mainCtx.drawImage(this.background, col * size, row * size);
    }

    @Override
    public int getWidth() {
        return cols;
    }

    @Override
    public int getHeight() {
        return rows;
    }

    private HTMLCanvasElement createCanvas(int rows, int cols) {
        HTMLCanvasElement canvas = (HTMLCanvasElement) document.createElement("canvas");
        canvas.width = cols * size;
        canvas.height = rows * size;
        return canvas;
    }

    private static CanvasRenderingContext2D getContext2D(HTMLCanvasElement main) {
        return (CanvasRenderingContext2D) (Object) main.getContext("2d");
    }



}
