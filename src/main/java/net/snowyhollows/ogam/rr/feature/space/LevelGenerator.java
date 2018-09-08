package net.snowyhollows.ogam.rr.feature.space;

import net.bajobongo.labyrinth.Backtracker;
import net.bajobongo.labyrinth.DeadEndRemover;
import net.bajobongo.labyrinth.model.rl.RlDirection;
import net.bajobongo.labyrinth.model.rl.RoguelikeLabyrinth;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.BentoInstance;
import net.snowyhollows.ogam.rr.core.Entity;

import java.util.Random;

public class LevelGenerator {

	private final Bento bento;
	private final int levelRows;
	private final int levelCols;

	@WithFactory
	public LevelGenerator(@ByFactory(BentoInstance.class) Bento bento, @ByName("level.height") int levelRows,
						  @ByName("level.width") int levelCols) {
		this.bento = bento;
		this.levelRows = levelRows;
		this.levelCols = levelCols;
	}

	public void generate() {
		Entity hero = bento.get("entity.character");
		hero.position.moveTo(Coords.of(1, 1));

		RoguelikeLabyrinth rl = new RoguelikeLabyrinth(levelCols, levelRows);
        DeadEndRemover<String, RlDirection> der = new DeadEndRemover<>();
		Backtracker backtracker = new Backtracker();
		backtracker.generate(
				rl.first(),
				(cell, dir) -> cell.connected().isEmpty() || Math.random() < 0.05, new Random());

        der.removeDeadEnds(rl.first());
		rl.read((row, col) -> {
			Entity e = bento.get("entity.wall"); e.position.setCoords(Coords.of(row, col));
		});
	}

}
