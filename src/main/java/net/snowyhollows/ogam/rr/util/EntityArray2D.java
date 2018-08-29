package net.snowyhollows.ogam.rr.util;

import net.snowyhollows.ogam.rr.core.Entity;

public final class EntityArray2D extends ObjectArray2D<Entity> {

	public EntityArray2D(int rows, int cols, Entity outsideObject, Entity nullObject, Mapper<Entity> mapper) {
		super(rows, cols, outsideObject, nullObject, mapper);
	}

}
