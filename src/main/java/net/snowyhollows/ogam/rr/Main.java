package net.snowyhollows.ogam.rr;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import net.snowyhollows.bento2.BentoRunner;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.factory.AllFactoryConfigs;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.LevelGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	public enum PlayerCommand {
    	LEFT, RIGHT, UP, DOWN, QUIT
	}

    public static void main(String[] args) throws IOException {
		BentoRunner.runWithClasspathProperties(MainFactory.IT, "/rr.properties");
    }

    @WithFactory
    public Main(AllFactoryConfigs allFactoryConfigs,
		    @ByFactory(ScreenFactory.class) Screen screen,
            LevelGenerator levelGenerator,
		    EntityEngine engine) {

		levelGenerator.generate();
	    List<Entity> displayList = new ArrayList<>();

		try {
			screen.startScreen();

			main_loop:
	        while (true) {
		        screen.clear();
		        displayList.clear();

		        int fromRow = 0;
		        int toRow = fromRow + screen.getTerminalSize().getRows();
		        int fromCol = 0;
		        int toCol = fromCol + screen.getTerminalSize().getColumns();

		        engine.forEachEntity(Mappers.position, e -> {
		        	Coords coords = e.position.getCoords();
		        	int row = coords.row;
		        	int col = coords.col;
		        	if (row >= fromRow && row <= toRow && col >= fromCol && col <= toCol) {
		        		displayList.add(e);
			        }
		        });

		        for (Entity entity : displayList) {
		        	if (entity.asciiRepresentation == null) {
		        		continue;
			        }
			        TextCharacter tc = new TextCharacter(
					        entity.asciiRepresentation.getChar(),
					        Util.colors.get(entity.asciiRepresentation.getColor()),
					        TextColor.ANSI.BLACK);
		        	Coords coords = entity.position.getCoords();
			        screen.setCharacter(coords.col, coords.row, tc);
		        }

		        screen.refresh();

		        PlayerCommand command = Util.commandFromKeyStroke(screen.readInput());
		        if (command == PlayerCommand.QUIT) {
			        break main_loop;
		        }

		        engine.forEach(Mappers.player, Mappers.position, (e,m) -> {
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

                engine.forEach(Mappers.gradient, Mappers.position, (gradient, position) -> {
                    gradient.clear();
                    gradient.create(position.getCoords(), 6, c -> {
                        for (Entity entity : displayList) {
                            if (entity.position.getCoords().equals(c) && entity.obstacle != null && entity.obstacle.isObstacleFor(null)) {
                                return false;
                            }
                        }
                        return true;
                    });

                    Entity gradientOrigin = engine.currentEntity();
                    engine.forEach(Mappers.gradientObserver, Mappers.position, (gradientObserver, observerPosition) -> {
                        if (gradient.get(observerPosition.getCoords()) < Integer.MAX_VALUE) {
                            gradientObserver.touchedBy(gradientOrigin);
                        }
                    });
                });
	        }


	        screen.stopScreen();
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
}
