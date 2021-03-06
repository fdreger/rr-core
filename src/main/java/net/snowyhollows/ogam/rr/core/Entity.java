package net.snowyhollows.ogam.rr.core;

import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.feature.ai.component.Actor;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.combat.component.Attackable;
import net.snowyhollows.ogam.rr.feature.combat.component.BasicAttributes;
import net.snowyhollows.ogam.rr.feature.combat.component.Destructible;
import net.snowyhollows.ogam.rr.feature.combat.component.GradientObserver;
import net.snowyhollows.ogam.rr.feature.space.Gradient;
import net.snowyhollows.ogam.rr.feature.space.component.Bumpable;
import net.snowyhollows.ogam.rr.feature.space.component.Position;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.component.Treadable;

public class Entity {
	public AsciiRepresentation asciiRepresentation;
	public PotentialObstacle obstacle;
	public Position position;
	public Treadable treadable;
	public Bumpable bumpable;
	public Player player;
	public Gradient gradient;
	public GradientObserver gradientObserver;
	public BasicAttributes basicAttributes;
	public Destructible destructible;
	public Attackable attackable;
	public Actor actor;
}
