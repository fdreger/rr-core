package net.snowyhollows.ogam.rr;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;

public enum Util {;

	public static final HashMap<AsciiRepresentation.Color, TextColor> colors = new HashMap<>();
	static {
		colors.put(AsciiRepresentation.Color.CYAN, TextColor.ANSI.CYAN);
		colors.put(AsciiRepresentation.Color.GREEN, TextColor.ANSI.GREEN);
		colors.put(AsciiRepresentation.Color.WHITE, TextColor.ANSI.WHITE);
		colors.put(AsciiRepresentation.Color.RED, TextColor.ANSI.RED);
		colors.put(AsciiRepresentation.Color.YELLOW, TextColor.ANSI.YELLOW);
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
