package net.snowyhollows.ogam.rr.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.ogam.rr.*;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystemFactory;

public class RrCore implements EntryPoint {
    private GameItself gameItself;
    private int COLS = 70;
    private int ROWS = 25;
    private GwtDisplaySystem gwtDisplaySystem;

    public void onModuleLoad() {
        Bento bento = Bento
                .createRoot();
        bento.register("level.width", COLS);
        bento.register("level.height", ROWS);
        gameItself = bento.get(GameItselfFactory.IT);
        gwtDisplaySystem = bento.get(GwtDisplaySystemFactory.IT);

        gwtDisplaySystem.addKeyPressHandler(k -> {
            PlayerCommand command = null;
            switch (k.getCharCode()) {
                case 'a': command = PlayerCommand.LEFT; break;
                case 'd': command = PlayerCommand.RIGHT; break;
                case 'w': command = PlayerCommand.UP; break;
                case 's': command = PlayerCommand.DOWN; break;
            }
            if (command != null) {
                gameItself.execute(command);
            }
            tick();
        });
        tick();
    }

    public void tick() {
        gameItself.step();
        gwtDisplaySystem.run();
    }
}
