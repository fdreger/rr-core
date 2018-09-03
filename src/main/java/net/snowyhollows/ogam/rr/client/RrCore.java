package net.snowyhollows.ogam.rr.client;

import com.google.gwt.core.client.EntryPoint;
import elemental2.promise.Promise;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.ogam.rr.*;

public class RrCore implements EntryPoint {
    private GameItself gameItself;
    private int COLS = 80;
    private int ROWS = 45;
    private GwtDisplaySystem gwtDisplaySystem;

    public void onModuleLoad() {
        Bento bento = Bento
                .createRoot();
        bento.register("level.width", COLS);
        bento.register("level.height", ROWS);
        gameItself = bento.get(GameItselfFactory.IT);
        gwtDisplaySystem = bento.get(GwtDisplaySystemFactory.IT);

        gwtDisplaySystem.keyboardListener = (k -> {
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

        gwtDisplaySystem.initiated.then(ignored -> Promise.resolve(tick()));
    }

    public Void tick() {
        gameItself.step();
        gwtDisplaySystem.run();
        return null;
    }
}
