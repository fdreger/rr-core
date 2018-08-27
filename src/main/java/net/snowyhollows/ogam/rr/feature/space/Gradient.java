package net.snowyhollows.ogam.rr.feature.space;

import java.util.HashMap;
import java.util.Map;

public class Gradient {
	private Map<Coords, Integer> values = new HashMap<>();

	private final class Info {
		final int distance;
		final boolean occupied;

		private Info(int distance, boolean occupied) {
			this.distance = distance;
			this.occupied = occupied;
		}
	}

	public interface GradientInformer {
		boolean isTraversable(Coords coords);
	}

	public int get(Coords coords) {
		return values.getOrDefault(coords, Integer.MAX_VALUE);
	}

	public void clear() {
		values.clear();
	}

	private static final Direction[] directions = Direction.values();

	public void create(Coords coords, int limit, GradientInformer informer) {
		createFrom(0, limit, informer, coords);
	}

	public void createFrom(int value, int limit, GradientInformer informer, Coords coords) {
		int current = values.getOrDefault(coords, Integer.MAX_VALUE);

		if (value >= current) {
			return;
		}

        if (value > 0 && (value >= limit || !informer.isTraversable(coords))) {
            return;
        }

		values.put(coords, value);

		for (Direction direction : directions) {
			createFrom(value + 1, limit, informer, direction.step(coords));
		}

	}

	public Direction follow(Coords coords) {
		int current = get(coords);

		for (Direction direction : directions) {
			if (get(direction.step(coords)) < current) {
				return direction;
			}
		}

		return Direction.ZERO;
	}

}
