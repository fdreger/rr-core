package net.snowyhollows.ogam.rr;

import java.util.HashMap;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import net.snowyhollows.ogam.rr.feature.ascii.component.Color;

public enum Util {;

	public static final HashMap<Color, TextColor> colors = new HashMap<>();
	static {
		colors.put(Color.CYAN, TextColor.ANSI.CYAN);
		colors.put(Color.GREEN, TextColor.ANSI.GREEN);
		colors.put(Color.WHITE, TextColor.ANSI.WHITE);
		colors.put(Color.RED, TextColor.ANSI.RED);
		colors.put(Color.YELLOW, TextColor.ANSI.YELLOW);
	}

	public static PlayerCommand commandFromKeyStroke(KeyStroke keyStroke) {
		switch (keyStroke.getKeyType()) {
			case ArrowUp: return PlayerCommand.UP;
			case ArrowDown: return PlayerCommand.DOWN;
			case ArrowLeft: return PlayerCommand.LEFT;
			case ArrowRight: return PlayerCommand.RIGHT;

			default:
				Character character = keyStroke.getCharacter();
				if (character != null) {
					switch (character) {
						case 'q': return PlayerCommand.QUIT;
					}
				}
		}
		return null;
	}
}
