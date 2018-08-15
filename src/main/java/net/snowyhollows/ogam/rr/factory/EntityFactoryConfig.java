package net.snowyhollows.ogam.rr.factory;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.lore.Door;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.space.component.impl.PositionImpl;
import net.snowyhollows.ogam.rr.feature.space.component.impl.PotentialObstacleImpl;

public class EntityFactoryConfig {

	@WithFactory
	public EntityFactoryConfig(@ByFactory(BentoInstance.class)Bento bento, EntityEngine engine) {

		bento.register("entity.character", (BentoFactory) b -> {
			Entity e = new Entity();
			e.player = new Player();
			e.position = new PositionImpl(engine, e);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.RED, '@');
			engine.addEntity(e);
			return e;
		});

		bento.register("entity.wall", (BentoFactory) b -> {
			Entity e = new Entity();
			e.obstacle = new PotentialObstacleImpl(true);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, '#');
			e.position = new PositionImpl(engine, e);
			engine.addEntity(e);
			return e;
		});

		bento.register("entity.door", (BentoFactory) b -> {
			Entity e = new Entity();
			Door door = new Door();
			e.obstacle = door;
			e.bumpable = door;
			e.asciiRepresentation = door;
			e.position = new PositionImpl(engine, e);
			engine.addEntity(e);
			return e;
		});

		bento.register("entity.nothing", (BentoFactory) b -> {
			Entity e = new Entity();
			e.position = new PositionImpl(engine, e);
			engine.addEntity(e);
			return e;
		});
	}

}
