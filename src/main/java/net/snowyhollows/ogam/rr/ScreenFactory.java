package net.snowyhollows.ogam.rr;

import java.io.IOException;
import java.nio.charset.Charset;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum ScreenFactory implements BentoFactory<Screen> {
	IT;

	@Override
	public Screen createInContext(Bento bento) {
		try {
			Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
			Screen screen = new TerminalScreen(terminal);
			return screen;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
