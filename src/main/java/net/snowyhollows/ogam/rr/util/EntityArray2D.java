package net.snowyhollows.ogam.rr.util;

import net.snowyhollows.ogam.rr.core.Entity;

public final class EntityArray2D extends ObjectArray2D<Entity> {

	public EntityArray2D(int rows, int cols, Entity outsideObject, Entity nullObject) {
		super(rows, cols, outsideObject, nullObject);
	}

}
