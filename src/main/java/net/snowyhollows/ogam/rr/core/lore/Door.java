package net.snowyhollows.ogam.rr.core.lore;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.Color;
import net.snowyhollows.ogam.rr.feature.space.component.Bumpable;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;

public class Door implements Bumpable, PotentialObstacle, AsciiRepresentation {

	private boolean closed = true;

	@Override
	public void bump(Entity other) {
		closed = false;
	}

	@Override
	public boolean isObstacleFor(Entity other) {
		return closed;
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public Color getBackgroundColor() {
		return Color.BLACK;
	}

	@Override
	public char getChar() {
		return closed ? '+' : '/';
	}
}
