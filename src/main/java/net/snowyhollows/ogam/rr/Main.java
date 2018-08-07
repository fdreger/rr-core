package net.snowyhollows.ogam.rr;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumMap;
import java.util.Optional;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import net.bajobongo.beach.engine.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.factory.AllFactoryConfigs;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.LevelGenerator;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.manipulator.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.MovementImpl;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.PotentialObstacleImpl;
import net.snowyhollows.ogam.rr.feature.space.util.MapOfLevel;

public class Main {


	public enum PlayerCommand {
    	LEFT, RIGHT, UP, DOWN, QUIT
	}

    public static void main(String[] args) throws IOException {
	    Bento.run(MainFactory.IT);
    }

    @WithFactory
    public Main(AllFactoryConfigs allFactoryConfigs,
		    @ByFactory(BentoInstance.class) Bento bento,
		    Space space,
		    @ByFactory(ScreenFactory.class) Screen screen,
            LevelGenerator levelGenerator){


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
	        }


	        screen.stopScreen();
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
}
