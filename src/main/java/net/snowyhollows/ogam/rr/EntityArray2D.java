package net.snowyhollows.ogam.rr;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.SomethingThatOccupiesSpace;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author efildre
 */
public final class EntityArray2D implements SomethingThatOccupiesSpace {
	private final int rows;
	private final int cols;
	private final Object[] entities;
	private final Entity outsideEntity;
	private final Entity nullEntity;

	public EntityArray2D(int rows, int cols, Entity outsideEntity, Entity nullEntity) {
		this.rows = rows;
		this.cols = cols;
		this.outsideEntity = outsideEntity;
		this.nullEntity = nullEntity;
		this.entities = new Object[rows * cols];;
	}

	public void fillWith(Entity entity) {
		Arrays.fill(entities, entity);
	}

	public boolean isValid(int row, int col) {
		return (row < rows && row >= 0 && col < cols && col >= 0);
	}

	public Entity get(int row, int col) {
		if (isValid(row, col)) {
			Entity result = (Entity)entities[row * cols + col];
			return result != null ? result : outsideEntity;
		} else return nullEntity;
	}

	public void put(int row, int col, Entity e) {
		if (isValid(row, col)) {
			entities[row * cols + col] = e;
		}
	}

	public String toString(Function<Entity, AsciiRepresentation> mapper) {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				AsciiRepresentation representation = mapper.apply(get(row, col));
				if (representation != null) {
					sb.append(representation.getChar());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void drawLine(int row1, int col1, int row2, int col2, Entity e) {
		int dr = row2 - row1;
		int dc = col2 - col1;
		float step = 0;

		if (Math.abs(dr) >= Math.abs(dc)) {
			step = Math.abs(dr);
		} else {
			step = Math.abs(dc);
		}

		float stepRow = dr / step;
		float stepCol = dc / step;

		float row = row1;
		float col = col1;

		for (int i = 0; i <= step; i++) {
			put((int)row, (int)col, e);
			row += stepRow;
			col += stepCol;
		}

	}

	@Override
	public Optional presentAt(Coords coords) {
		return Optional.ofNullable(get(coords.row, coords.col));
	}
}
