package net.snowyhollows.ogam.rr;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import net.snowyhollows.ogam.rr.core.impl.EntityImpl;
import net.snowyhollows.ogam.rr.feature.ascii.manipulator.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.manipulator.impl.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.manipulator.Movement;
import net.snowyhollows.ogam.rr.feature.space.manipulator.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.MovementImpl;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.PotentialObstacleImpl;
import net.snowyhollows.ogam.rr.feature.space.util.MapOfLevel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumMap;
import java.util.Optional;

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
        EntityImpl wall = new EntityImpl();
        wall.addManipulator(new PotentialObstacleImpl(true));
        wall.addManipulator(new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, '#'));

        Space space = new Space();


        Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        EntityImpl hero = new EntityImpl();
        hero.addManipulator(new MovementImpl(space, hero));
        hero.addManipulator(new PotentialObstacleImpl(true));
        hero.addManipulator(new AsciiRepresentationImpl(AsciiRepresentation.Color.RED, '@'));

        hero.manipulate(Movement.class).map(m -> {
            m.setPosition(new Coords(1, 4));
            return null;
        });

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

        wall.addManipulator(potentialObstacle);
        space.addSomethingThatOccupiesSpace(mol);

        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 40; col++) {
                Coords coords = new Coords(row, col);
                Optional<AsciiRepresentation> manipulator = space.entitiesAt(coords, x -> true).stream().findFirst().flatMap(e -> e.manipulate(AsciiRepresentation.class));
                final int r = row;
                final int c = col;
                manipulator.map(rep -> {
                    screen.setCharacter(c, r, new TextCharacter(rep.getChar(), colors.get(rep.getColor()), TextColor.ANSI.BLACK));
                    return null;
                });
            }
        }

        screen.refresh();
        screen.readInput();

        screen.stopScreen();
    }
}
