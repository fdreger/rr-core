package net.snowyhollows.ogam.rr.feature.space;

import net.bajobongo.beach.engine.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.DividerGenerator;
import net.snowyhollows.ogam.rr.EngineFactory;
import net.snowyhollows.ogam.rr.EntityArray2D;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.util.MapOfLevel;

/**
 * @author efildre
 */
public class LevelGenerator {

	private final Bento bento;
	private Space space;
	private final DividerGenerator dividerGenerator;
	Entity wall;
	Entity nothing;
	private Engine engine;

	@WithFactory
	public LevelGenerator(@ByFactory(BentoInstance.class) Bento bento,
						  Space space,
						  @ByFactory(EngineFactory.class) Engine engine,
						  DividerGenerator dividerGenerator) {
		this.bento = bento;
		this.space = space;
		this.engine = engine;
		this.dividerGenerator = dividerGenerator;
	}

	public void generate() {
		Entity hero = bento.get("entity.character");
		engine.addEntity(hero);

		hero.movement.setPosition(new Coords(1, 4));

		EntityArray2D entityArray2D = new EntityArray2D(100, 100, null, null);
		dividerGenerator.render(entityArray2D, () -> bento.get("entity.door"));
		space.addSomethingThatOccupiesSpace(entityArray2D);

		//		MapOfLevel mol = new MapOfLevel();
//		mol.addMapping('#', wall);
//		mol.addMapping(' ', nothing);
//		mol.addRow("#######################################");
//		mol.addRow("##                  ######### #### ####");
//		mol.addRow("################         #### #### ####");
//		mol.addRow("#####     ###########   ##### #### ####");
//		mol.addRow("#####     ############+######      ####");
//		mol.addRow("#####     ############ ###### #### ####");
//		mol.addRow("#####     ############ ######+#### ####");
//		mol.addRow("#########   ########## ######      ####");
//		mol.addRow("##########   #  ######+########### ####");
//		mol.addRow("##########    ########        #### ####");
//		mol.addRow("########               ###### #### ####");
//		mol.addRow("##########         #####           ####");
//		mol.addRow("#######################################");
//
//		mol.visitCoords((coords, ch) -> {
//			Entity e = null;
//			switch (ch) {
//				case '+': e = bento.get("entity.door");
//			}
//			if (e != null) {
//				e.movement.setPosition(coords);
//				engine.addEntity(e);
//			}
//		});
	}

}
