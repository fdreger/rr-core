package net.snowyhollows.ogam.rr.core;

import net.bajobongo.beach.engine.ComponentMapper;
import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.feature.ai.component.Actor;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.combat.component.Attackable;
import net.snowyhollows.ogam.rr.feature.combat.component.Destructible;
import net.snowyhollows.ogam.rr.feature.combat.component.GradientObserver;
import net.snowyhollows.ogam.rr.feature.space.Gradient;
import net.snowyhollows.ogam.rr.feature.space.component.Bumpable;
import net.snowyhollows.ogam.rr.feature.space.component.Position;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.component.Treadable;

public class Mappers {

	public final static ComponentMapper<Treadable, Entity> treadable
			= new ComponentMapper<>(e -> e.treadable, (e, t) -> e.treadable = t);
	public final static ComponentMapper<Position, Entity> position
			= new ComponentMapper<>(e -> e.position, (e, t) -> e.position = t);
	public final static ComponentMapper<Bumpable, Entity> bumpable
			= new ComponentMapper<>(e -> e.bumpable, (e, t) -> e.bumpable= t);
	public final static ComponentMapper<PotentialObstacle, Entity> obstacle
			= new ComponentMapper<>(e -> e.obstacle, (e, t) -> e.obstacle = t);
	public final static ComponentMapper<Player, Entity> player
			= new ComponentMapper<>(e -> e.player, (e, t) -> e.player = t);
	public final static ComponentMapper<AsciiRepresentation, Entity> asciiRepresentation
			= new ComponentMapper<>(e -> e.asciiRepresentation, (e, t) -> e.asciiRepresentation= t);
	public final static ComponentMapper<Gradient, Entity> gradient
			= new ComponentMapper<>(e -> e.gradient, (e, t) -> e.gradient = t);
	public final static ComponentMapper<GradientObserver, Entity> gradientObserver
			= new ComponentMapper<>(e -> e.gradientObserver , (e, t) -> e.gradientObserver = t);
	public final static ComponentMapper<Attackable, Entity> attackable
			= new ComponentMapper<>(e -> e.attackable, (e, t) -> e.attackable = t);
	public final static ComponentMapper<Destructible, Entity> destructible
			= new ComponentMapper<>(e -> e.destructible, (e, t) -> e.destructible= t);
	public final static ComponentMapper<Actor, Entity> actor
			= new ComponentMapper<>(e -> e.actor, (e, t) -> e.actor = t);
}
