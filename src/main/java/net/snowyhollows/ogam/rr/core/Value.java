package net.snowyhollows.ogam.rr.core;

import java.util.Random;

public class Value {
	private final Random random;
	private final int bonus;
	private final int sides;
	private final int times;

	public Value(Random random, int bonus, int sides, int times) {
		this.random = random;
		this.bonus = bonus;
		this.sides = sides;
		this.times = times;
	}

	public int eval() {
		int result = bonus;
		for (int i = 0; i < times; i++) {
			result += random.nextInt(sides) + 1;
		}
		return result;
	}
}
