package net.snowyhollows.ogam.rr.feature.space;

import net.bajobongo.beach.engine.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.DividerGenerator;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.util.ObjectArray2D;
import net.snowyhollows.ogam.rr.core.Entity;

public class LevelGenerator {

	private final Bento bento;
	private final DividerGenerator dividerGenerator;
	private Engine engine;

	@WithFactory
	public LevelGenerator(@ByFactory(BentoInstance.class) Bento bento,
						  EntityEngine engine,
						  DividerGenerator dividerGenerator) {
		this.bento = bento;
		this.engine = engine;
		this.dividerGenerator = dividerGenerator;
	}

	public void generate() {
		Entity hero = bento.get("entity.character");
		engine.addEntity(hero);

		hero.position.setCoords(new Coords(1, 4));

		ObjectArray2D entityArray2D = new ObjectArray2D(100, 100, null, null);
		dividerGenerator.render(entityArray2D, () -> bento.get("entity.door"));
	}

}
