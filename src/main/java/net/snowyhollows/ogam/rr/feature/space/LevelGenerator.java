package net.snowyhollows.ogam.rr.feature.space;

import net.bajobongo.beach.engine.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.EngineFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.util.MapOfLevel;

/**
 * @author efildre
 */
public class LevelGenerator {

	private final Bento bento;
	private Space space;
	Entity wall;
	Entity nothing;
	private Engine<Entity> engine;

	@WithFactory
	public LevelGenerator(@ByFactory(BentoInstance.class) Bento bento,
			Space space,
			@ByFactory(EngineFactory.class) Engine engine) {
		this.bento = bento;
		this.space = space;
		wall = bento.get("entity.wall");
		nothing = bento.get("entity.nothing");
		this.engine = engine;
		engine.addEntity(wall);
		engine.addEntity(nothing);
	}

	public void generate() {
		Entity hero = bento.get("entity.character");
		engine.addEntity(hero);
		hero.movement.setPosition(new Coords(1, 4));


		MapOfLevel mol = new MapOfLevel();
		mol.addMapping('#', wall);
		mol.addMapping(' ', nothing);
		mol.addRow("#######################################");
		mol.addRow("##                  ######### #### ####");
		mol.addRow("################         #### #### ####");
		mol.addRow("#####     ###########   ##### #### ####");
		mol.addRow("#####     ############ ######      ####");
		mol.addRow("#####     ############ ###### #### ####");
		mol.addRow("#####     ############ ###### #### ####");
		mol.addRow("#########   ########## ######      ####");
		mol.addRow("##########   #  ###### ########### ####");
		mol.addRow("##########    ########        #### ####");
		mol.addRow("########               ###### #### ####");
		mol.addRow("##########         #####           ####");
		mol.addRow("#######################################");

		space.addSomethingThatOccupiesSpace(mol);
	}

}
