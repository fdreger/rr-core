package net.snowyhollows.ogam.rr.feature.space;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.DividerGenerator;
import net.snowyhollows.ogam.rr.EntityEngine;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.core.Mappers;
import net.snowyhollows.ogam.rr.util.Buffer2D;

public class LevelGenerator {

	private final Bento bento;
	private final DividerGenerator dividerGenerator;
	private EntityEngine engine;

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
		hero.position.setCoords(new Coords(1, 4));

        Buffer2D<Entity> buffer = new Buffer2D<>(100, 100, engine);
		dividerGenerator.render(buffer,
				(r, c) -> { Entity e = bento.get("entity.monster"); e.position.setCoords(new Coords(r, c)); },
				(r, c) -> { Entity e = bento.get("entity.wall"); e.position.setCoords(new Coords(r, c)); },
				(r, c) -> {
		            engine.forEach(Mappers.position, p -> {
		                if (p.getCoords().col == c && p.getCoords().row == r) {
		                    engine.removeCurrentEntity();
                        }
                    });
		        }
		);
	}

}
