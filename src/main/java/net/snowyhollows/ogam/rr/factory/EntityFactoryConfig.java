package net.snowyhollows.ogam.rr.factory;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.Player;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.MessageLog;
import net.snowyhollows.ogam.rr.core.lore.Door;
import net.snowyhollows.ogam.rr.core.lore.Monster;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.combat.component.impl.AttackableImpl;
import net.snowyhollows.ogam.rr.feature.combat.component.impl.DestructibleImpl;
import net.snowyhollows.ogam.rr.feature.combat.component.impl.GradientObserverImpl;
import net.snowyhollows.ogam.rr.feature.space.Gradient;
import net.snowyhollows.ogam.rr.feature.space.component.impl.PositionImpl;
import net.snowyhollows.ogam.rr.feature.space.component.impl.PotentialObstacleImpl;

public class EntityFactoryConfig {

	@WithFactory
	public EntityFactoryConfig(@ByFactory(BentoInstance.class)Bento bento, EntityEngine engine, MessageLog messageLog) {

		bento.register("entity.character", (BentoFactory) b -> {
			Entity e = new Entity();
            Player player = new Player();
            AttackableImpl attackable = new AttackableImpl(e, messageLog);
            e.player = player;
			e.obstacle = player;
			e.basicAttributes = e.player;
			e.attackable = attackable;
			e.bumpable = attackable;
			e.position = new PositionImpl(engine, e);
            e.destructible = new DestructibleImpl(20);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.RED, '@');
			e.gradient = new Gradient();
			engine.addEntity(e);
			return e;
		});

		bento.register("entity.monster", (BentoFactory) b -> {
			Entity e = new Entity();
			e.gradientObserver = new GradientObserverImpl(e);
			Monster monster = new Monster(e);
			e.basicAttributes = monster;
			e.gradientObserver = monster;
			e.actor = monster;
			AttackableImpl attackable = new AttackableImpl(e, messageLog);
			e.attackable = attackable;
            e.obstacle = attackable;
            e.bumpable = attackable;
			e.destructible = new DestructibleImpl(10);
			e.position = new PositionImpl(engine, e);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.YELLOW, 'O');
			engine.addEntity(e);
			return e;
		});

		bento.register("entity.wall", (BentoFactory) b -> {
			Entity e = new Entity();
			e.obstacle = new PotentialObstacleImpl(true);
			e.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, AsciiRepresentation.Color.GREEN, '#');
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
			e.gradientObserver = new GradientObserverImpl(e);
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
