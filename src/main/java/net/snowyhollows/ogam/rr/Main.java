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
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;

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

    FovFow fovFow;

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

                engine.forEach(Mappers.player, Mappers.position, (e,m) -> {

                    fovFow = e.fovFow;
                    e.fovFow.createFrom(m.getCoords().row, m.getCoords().col, 10, 1, c -> {
                        for (Entity entity : displayList) {
                            if (entity.obstacle != null &&
                                    entity.position.getCoords().equals(c)
                                    && (!entity.obstacle.isObstacleFor(null)
                                    || !entity.obstacle.isTemporary())
                                    ) {
                                return 0;
                            }
                        }
                        return 1;
                    });
                });

                if (fovFow != null) {
                    fovFow.forEachVisible(coords -> {
                        TextCharacter tc = new TextCharacter(
                                '.',
                                TextColor.ANSI.YELLOW,
                                TextColor.ANSI.BLACK);
                        screen.setCharacter(coords.col - fromCol, coords.row - fromRow, tc);
                    });

                }

		        for (Entity entity : displayList) {
		        	if (entity.asciiRepresentation == null) {
		        		continue;
			        }

					Coords coords = entity.position.getCoords();

                    if (
                            (fovFow != null) && (
                                    fovFow.isVisible(coords)
                                    || (fovFow.isSeen(coords) && entity.obstacle != null && !entity.obstacle.isTemporary())
                                    )) {
                        TextCharacter tc = new TextCharacter(
                                entity.asciiRepresentation.getChar(),
                                Util.colors.get(entity.asciiRepresentation.getColor()),
                                TextColor.ANSI.BLACK);
                        screen.setCharacter(coords.col - fromCol, coords.row - fromRow, tc);
                    }

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
                    fovFow = e.fovFow;


		        });

		        engine.forEach(Mappers.destructible, d -> {
		        	if (d.isDestroyed()) {
						System.out.println(engine.currentEntity() + " is destroyed");
		        		engine.removeCurrentEntity();
					}
				});

		        engine.forEach(Mappers.actor, a -> {
		        	a.act();
				});

                engine.forEach(Mappers.gradient, Mappers.position, (gradient, position) -> {
                    gradient.clear();
                    gradient.create(position.getCoords(), 10, c -> {
                        for (Entity entity : displayList) {
                            if (entity.position.getCoords().equals(c) && entity.obstacle != null && entity.obstacle.isObstacleFor(null) && !entity.obstacle.isTemporary()) {
                                return false;
                            }
                        }
                        return true;
                    });

                    engine.forEach(Mappers.gradientObserver, Mappers.position, (gradientObserver, observerPosition) -> {
                        if (gradient.get(observerPosition.getCoords()) < Integer.MAX_VALUE) {
                            gradientObserver.touchedBy(gradient);
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
