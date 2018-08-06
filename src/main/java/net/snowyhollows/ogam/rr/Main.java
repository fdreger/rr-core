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
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.manipulator.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.MovementImpl;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.PotentialObstacleImpl;
import net.snowyhollows.ogam.rr.feature.space.util.MapOfLevel;

public class Main {
    private static final EnumMap<AsciiRepresentation.Color, TextColor> colors = new EnumMap<AsciiRepresentation.Color, TextColor>(AsciiRepresentation.Color.class);
    static {
        colors.put(AsciiRepresentation.Color.CYAN, TextColor.ANSI.CYAN);
        colors.put(AsciiRepresentation.Color.GREEN, TextColor.ANSI.GREEN);
        colors.put(AsciiRepresentation.Color.WHITE, TextColor.ANSI.WHITE);
        colors.put(AsciiRepresentation.Color.RED, TextColor.ANSI.RED);
        colors.put(AsciiRepresentation.Color.YELLOW, TextColor.ANSI.YELLOW);
    }

    public static void main(String[] args) throws IOException {
        PotentialObstacle potentialObstacle = new PotentialObstacleImpl(true);
        Entity wall = new Entity();

        Engine<Entity> engine = new Engine<>();

        engine.addEntity(wall);

	    AsciiRepresentationImpl reprNothing = new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, ' ');
        wall.obstacle = new PotentialObstacleImpl(true);
        wall.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, '#');

        Space space = new Space();

        Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Entity hero = new Entity();
        hero.movement = new MovementImpl(space, hero);
        hero.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.RED, '@');

        hero.movement.setPosition(new Coords(1, 4));

        MapOfLevel mol = new MapOfLevel();
        mol.addMapping('#', wall);
        mol.addRow("#######################################");
        mol.addRow("##                  ######### #### ####");
        mol.addRow("################         #### #### ####");
        mol.addRow("#####     ###########   ##### #### ####");
        mol.addRow("#####     ############ ######      ####");
        mol.addRow("#####     ############ ###### #### ####");
        mol.addRow("#####     ############ ###### #### ####");
        mol.addRow("##########  ########## ######      ####");
        mol.addRow("##########   #  ###### ########### ####");
        mol.addRow("##########    ######## ########### ####");
        mol.addRow("########               ########### ####");
        mol.addRow("##########         #####           ####");
        mol.addRow("#######################################");

        space.addSomethingThatOccupiesSpace(mol);

        main_loop:
        while (true) {
	        screen.clear();
	        for (int row = 0; row < 15; row++) {
		        for (int col = 0; col < 40; col++) {
			        Coords coords = new Coords(row, col);
			        AsciiRepresentation ascii = space
					        .entitiesAt(coords, x -> true).stream()
					        .findFirst()
					        .map(e -> e.asciiRepresentation)
					        .orElse(reprNothing);
			        screen.setCharacter(
			        		col, row,
					        new TextCharacter(
					        		ascii.getChar(),
							        colors.get(ascii.getColor()),
							        TextColor.ANSI.BLACK));
		        }
	        }

	        screen.refresh();
	        KeyStroke keyStroke = screen.readInput();
	        switch (keyStroke.getKeyType()) {
		        case ArrowUp:
		        	break;
		        case ArrowDown:
			        break;
		        case ArrowLeft:
			        break;
		        case ArrowRight:
			        break;
		        default:
					break main_loop;
	        }
        }

        screen.stopScreen();
    }
}
