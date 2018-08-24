package net.snowyhollows.ogam.rr.core;

import java.util.Random;

public class Value {
	public final static Random RANDOM = new Random();
	public final static Value ZERO = new Value(RANDOM, 0, 0);
	public final static Value ONE = new Value(RANDOM, 1, 1);

	public enum ComparisonResult {
	    WON, LOST, TIE
    }

	private final Random random;
	private final int sides;
	private final int times;

	public Value(Random random, int sides, int times) {
		this.random = random;
		this.sides = sides;
		this.times = times;
	}

	public Value(int constant) {
	    this.random = null;
	    this.sides = 1;
	    this.times = constant;
    }

	public int eval() {
	    if (sides == 1) {
	        return times;
        }

		int result = 0;
		for (int i = 0; i < times; i++) {
			result += random.nextInt(sides) + 1;
		}
		return result;
	}

	public ComparisonResult rollAgainst(Value other) {
	    int me = eval();
	    int against = other.eval();

	    if (me == against) {
	        return ComparisonResult.TIE;
        } else if (me > against) {
	        return ComparisonResult.WON;
        } else {
	        return ComparisonResult.LOST;
        }

    }


}
