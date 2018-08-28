package net.snowyhollows.ogam.rr;

import com.googlecode.lanterna.screen.Screen;
import net.snowyhollows.bento2.BentoRunner;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.factory.AllFactoryConfigs;
import net.snowyhollows.ogam.rr.feature.ai.system.ActorSystem;
import net.snowyhollows.ogam.rr.feature.ascii.system.AsciiDisplaySystem;
import net.snowyhollows.ogam.rr.feature.combat.system.DestructibleSystem;
import net.snowyhollows.ogam.rr.feature.combat.system.GradientSystem;
import net.snowyhollows.ogam.rr.feature.space.Direction;
import net.snowyhollows.ogam.rr.feature.space.LevelGenerator;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.component.FovFow;
import net.snowyhollows.ogam.rr.feature.space.system.FovSystem;

import java.io.IOException;

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
		    DestructibleSystem destructibleSystem,
		    ActorSystem actorSystem,
		    GradientSystem gradientSystem,
		    DisplayListSystem displayListSystem,
		    AsciiDisplaySystem asciiDisplaySystem,
		    PlayerSystem playerSystem,
		    FovSystem fovSystem) {

		levelGenerator.generate();

		try {
			screen.startScreen();

			main_loop:
	        while (true) {
		        screen.clear();
		        displayListSystem.displayList.clear();

		        destructibleSystem.run();
		        actorSystem.run();
		        gradientSystem.run();
		        displayListSystem.run();
		        fovSystem.run();
		        asciiDisplaySystem.run();
		        PlayerCommand command = Util.commandFromKeyStroke(screen.readInput());

                playerSystem.run(command);

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
