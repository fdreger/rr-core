package net.snowyhollows.ogam.rr;

import java.io.IOException;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import net.bajobongo.beach.engine.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoRunner;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.factory.AllFactoryConfigs;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.LevelGenerator;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.component.Movement;

public class Main {


	public enum PlayerCommand {
    	LEFT, RIGHT, UP, DOWN, QUIT
	}

    public static void main(String[] args) throws IOException {
		BentoRunner.runWithClasspathProperties(MainFactory.IT, "/rr.properties");
    }

    @WithFactory
    public Main(AllFactoryConfigs allFactoryConfigs,
		    @ByFactory(BentoInstance.class) Bento bento,
		    Space space,
		    @ByFactory(ScreenFactory.class) Screen screen,
            LevelGenerator levelGenerator,
		    @ByFactory(EngineFactory.class) Engine eng){
		Engine<Entity> engine = eng;


		levelGenerator.generate();

		try {

			screen.startScreen();

			main_loop:
	        while (true) {
		        screen.clear();
		        for (int row = 0; row < 15; row++) {
			        for (int col = 0; col < 40; col++) {
				        Coords coords = new Coords(row, col);
				        TextCharacter tc = space
						        .entitiesAt(coords, x -> true).stream()
						        .findFirst()
						        .map(e -> e.asciiRepresentation)
						        .map(ascii -> new TextCharacter(
								        ascii.getChar(),
								        Util.colors.get(ascii.getColor()),
								        TextColor.ANSI.BLACK))
						        .orElse(null);
				        if (tc != null) {
					        screen.setCharacter(col, row, tc);
				        }
			        }
		        }

		        screen.refresh();

		        PlayerCommand command = Util.commandFromKeyStroke(screen.readInput());
		        if (command == PlayerCommand.QUIT) {
			        break main_loop;
		        }

		        engine.forEach(Mappers.player, Mappers.movement, (e,m) -> {
		        	if (command == null) {
		        		return;
					}

			        switch (command) {
				        case UP: m.move(Direction.N); break;
				        case DOWN: m.move(Direction.S); break;
				        case LEFT: m.move(Direction.W); break;
				        case RIGHT: m.move(Direction.E); break;
			        }
		        });

	        }


	        screen.stopScreen();
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
}
