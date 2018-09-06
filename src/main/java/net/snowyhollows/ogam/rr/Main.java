package net.snowyhollows.ogam.rr;

import com.googlecode.lanterna.screen.Screen;
import net.snowyhollows.bento2.BentoRunner;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.feature.ascii.system.SwingDisplaySystem;

import java.io.IOException;

public class Main {
    private GameItself gameItself;

    public static void main(String[] args) throws IOException {
		BentoRunner.runWithClasspathProperties(MainFactory.IT, "/rr.properties");
    }

    @WithFactory
    public Main(
		    @ByFactory(ScreenFactory.class) Screen screen,
			SwingDisplaySystem swingDisplaySystem,
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
		        swingDisplaySystem.run();
	        }

	        screen.stopScreen();
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
}
