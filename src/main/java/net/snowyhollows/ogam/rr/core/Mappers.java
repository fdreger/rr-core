package net.snowyhollows.ogam.rr.core;

import net.bajobongo.beach.engine.ComponentMapper;
import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.feature.space.component.Bumpable;
import net.snowyhollows.ogam.rr.feature.space.component.Movement;
import net.snowyhollows.ogam.rr.feature.space.component.PotentialObstacle;
import net.snowyhollows.ogam.rr.feature.space.component.Treadable;

/**
 * @author efildre
 */
public class Mappers {

	public final static ComponentMapper<Treadable, Entity> treadable
			= new ComponentMapper<>(e -> e.treadable, (e, t) -> e.treadable = t);
	public final static ComponentMapper<Movement, Entity> movement
			= new ComponentMapper<>(e -> e.movement, (e, t) -> e.movement = t);
	public final static ComponentMapper<Bumpable, Entity> bumpable
			= new ComponentMapper<>(e -> e.bumpable, (e, t) -> e.bumpable= t);
	public final static ComponentMapper<PotentialObstacle, Entity> obstacle
			= new ComponentMapper<>(e -> e.obstacle, (e, t) -> e.obstacle = t);
	public final static ComponentMapper<Player, Entity> player
			= new ComponentMapper<>(e -> e.player, (e, t) -> e.player = t);


}
