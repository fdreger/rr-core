/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.ogam.rr;

import java.util.EnumMap;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;

/**
 * @author efildre
 */
public enum Util {;

	public static final EnumMap<AsciiRepresentation.Color, TextColor> colors = new EnumMap<AsciiRepresentation.Color, TextColor>(AsciiRepresentation.Color.class);
	static {
		colors.put(AsciiRepresentation.Color.CYAN, TextColor.ANSI.CYAN);
		colors.put(AsciiRepresentation.Color.GREEN, TextColor.ANSI.GREEN);
		colors.put(AsciiRepresentation.Color.WHITE, TextColor.ANSI.WHITE);
		colors.put(AsciiRepresentation.Color.RED, TextColor.ANSI.RED);
		colors.put(AsciiRepresentation.Color.YELLOW, TextColor.ANSI.YELLOW);
	}

	public static Main.PlayerCommand commandFromKeyStroke(KeyStroke keyStroke) {
		switch (keyStroke.getKeyType()) {
			case ArrowUp: return Main.PlayerCommand.UP;
			case ArrowDown: return Main.PlayerCommand.DOWN;
			case ArrowLeft: return Main.PlayerCommand.LEFT;
			case ArrowRight: return Main.PlayerCommand.RIGHT;
			default:
				switch (keyStroke.getCharacter()) {
					case 'q': return Main.PlayerCommand.QUIT;
				}
		}
		return null;
	}
}
