/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.ogam.rr.feature.space;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.feature.space.util.MapOfLevel;

/**
 * @author efildre
 */
public class LevelGenerator {

	private final Bento bento;
	private Space space;
	Entity wall;
	Entity nothing;

	@WithFactory
	public LevelGenerator(@ByFactory(BentoInstance.class) Bento bento, Space space) {
		this.bento = bento;
		this.space = space;
		wall = bento.get("entity.wall");
		nothing = bento.get("entity.nothing");
	}

	public void generate() {
		AsciiRepresentation reprNothing = new AsciiRepresentationImpl(AsciiRepresentation.Color.WHITE, ' ');
		Entity hero = bento.get("entity.character");
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
		mol.addRow("##########  ########## ######      ####");
		mol.addRow("##########   #  ###### ########### ####");
		mol.addRow("##########    ######## ########### ####");
		mol.addRow("########               ########### ####");
		mol.addRow("##########         #####           ####");
		mol.addRow("#######################################");

		space.addSomethingThatOccupiesSpace(mol);
	}

}
