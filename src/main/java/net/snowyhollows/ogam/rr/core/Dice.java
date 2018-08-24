package net.snowyhollows.ogam.rr.core;

import java.util.Random;

import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;

public class Dice {
	private Random random;

	@WithFactory
	public Dice(@ByFactory(RandomFactory.class) Random random) {
		this.random = random;
	}

	public Value constant(int c) {
		return new Value(random, 1, c);
	}

	public Value roll(int rolls, int sides, int bonus) {
		return new Value(random, sides, rolls);
	}
}

