package net.snowyhollows.ogam.rr.client;

import com.google.gwt.core.client.EntryPoint;
import elemental2.promise.Promise;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.ogam.rr.GameItself;
import net.snowyhollows.ogam.rr.GameItselfFactory;
import net.snowyhollows.ogam.rr.PlayerCommand;
import net.snowyhollows.ogam.rr.core.MessageLogFactory;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;

public class RrCore implements EntryPoint {
    private GameItself gameItself;
    private int COLS = 80;
    private int ROWS = 45;

    public void onModuleLoad() {
        Bento bento = Bento
                .createRoot();
        bento.register("level.width", COLS);
        bento.register("level.height", ROWS);
        GwtAsciiPanel gwtAsciiPanel = new GwtAsciiPanel(COLS, ROWS, 10, AsciiRepresentation.Color.BLACK);
        gwtAsciiPanel.setKeyboardListener(k -> {
            PlayerCommand command = null;
            switch (k) {
                case "a": case "ArrowLeft": command = PlayerCommand.LEFT; break;
                case "d": case "ArrowRight": command = PlayerCommand.RIGHT; break;
                case "w": case "ArrowUp": command = PlayerCommand.UP; break;
                case "s": case "ArrowDown": command = PlayerCommand.DOWN; break;
            }
            if (command != null) {
                gameItself.execute(command);
            }
            tick();
        });
        bento.register("asciiPanel", gwtAsciiPanel);
        gameItself = bento.get(GameItselfFactory.IT);
        bento.get(MessageLogFactory.IT).info("game started...");

        gwtAsciiPanel.getInitiated().then(ignored -> Promise.resolve(startGame()));
    }

    public Void startGame() {
        tick();
        return null;
    }

    public void tick() {
        gameItself.step();
    }
}
