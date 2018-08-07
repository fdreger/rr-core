package net.snowyhollows.ogam.rr.factory;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.space.Space;
import net.snowyhollows.ogam.rr.feature.space.component.impl.MovementImpl;
import net.snowyhollows.ogam.rr.feature.space.component.impl.PotentialObstacleImpl;

/**
 * @author efildre
 */
public class EntityFactoryConfig {

	@WithFactory
	public EntityFactoryConfig(@ByFactory(BentoInstance.class)Bento bento, Space space) {

		bento.register("entity.character", (BentoFactory) b -> {
			Entity e = new Entity();
			e.player = new Player();
			e.movement = new MovementImpl(space, e);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.RED, '@');
			return e;
		});

		bento.register("entity.wall", (BentoFactory) b -> {
			Entity e = new Entity();
			e.obstacle = new PotentialObstacleImpl(true);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, '#');
			return e;
		});
		bento.register("entity.nothing", (BentoFactory) b -> {
			Entity e = new Entity();
			return e;
		});
	}


	// helper, to make the syntax nicer
	private void register(Bento bento, String key, BentoFactory bf) {
		bento.register(key, bf);
	}
}
