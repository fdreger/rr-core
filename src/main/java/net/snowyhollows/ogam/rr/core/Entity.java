package net.snowyhollows.ogam.rr.core;

import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.space.component.Bumpable;
import net.snowyhollows.ogam.rr.feature.space.component.Movement;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.component.Treadable;
import net.snowyhollows.ogam.rr.feature.space.component.impl.MovementImpl;

public class Entity {
	public AsciiRepresentation asciiRepresentation;
	public PotentialObstacle obstacle;
	public Movement movement;
	public Treadable treadable;
	public Bumpable bumpable;
	public Player player;
}
