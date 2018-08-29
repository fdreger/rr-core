package net.snowyhollows.ogam.rr;

import com.googlecode.lanterna.screen.Screen;
import net.snowyhollows.bento2.BentoRunner;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.factory.AllFactoryConfigs;
import net.snowyhollows.ogam.rr.feature.ai.system.ActorSystem;
import net.snowyhollows.ogam.rr.feature.ascii.system.AsciiDisplaySystem;
import net.snowyhollows.ogam.rr.feature.combat.system.DestructibleSystem;
import net.snowyhollows.ogam.rr.feature.combat.system.GradientSystem;
import net.snowyhollows.ogam.rr.feature.space.LevelGenerator;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.system.FovSystem;

import java.io.IOException;

public class Main {
    private GameItself gameItself;

    public static void main(String[] args) throws IOException {
		BentoRunner.runWithClasspathProperties(MainFactory.IT, "/rr.properties");
    }

    @WithFactory
    public Main(
		    @ByFactory(ScreenFactory.class) Screen screen,
			AsciiDisplaySystem asciiDisplaySystem,
			GameItself gameItself) {

		try {
			screen.startScreen();

			main_loop:
	        while (true) {
		        screen.clear();
		        gameItself.step();

		        PlayerCommand command = Util.commandFromKeyStroke(screen.readInput());
                gameItself.execute(command);

		        if (command == PlayerCommand.QUIT) {
			        break main_loop;
		        }
		        asciiDisplaySystem.run();
	        }

	        screen.stopScreen();
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
}
