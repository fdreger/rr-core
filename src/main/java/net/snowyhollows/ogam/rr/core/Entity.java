package net.snowyhollows.ogam.rr.core;

import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.space.manipulator.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.manipulator.impl.MovementImpl;

public class Entity {
	public AsciiRepresentation asciiRepresentation;
	public PotentialObstacle obstacle;

	public MovementImpl movement;
}
