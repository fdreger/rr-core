package net.snowyhollows.ogam.rr.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.space.Coords;

public class ObjectArray2D<T> {
	private final int rows;
	private final int cols;
	private final Object[] entities;
	private final T outsideObject;
	private final T nullObject;

	public ObjectArray2D(int rows, int cols, T outsideObject, T nullObject) {
		this.rows = rows;
		this.cols = cols;
		this.outsideObject = outsideObject;
		this.nullObject = nullObject;
		this.entities = new Object[rows * cols];;
	}

	public void fillWith(T entity) {
		Arrays.fill(entities, entity);
	}

	public boolean isValid(int row, int col) {
		return (row < rows && row >= 0 && col < cols && col >= 0);
	}

	public T get(int row, int col) {
		if (isValid(row, col)) {
			T result = (T)entities[row * cols + col];
			return result != null ? result : outsideObject;
		} else return nullObject;
	}

	public void put(int row, int col, T e) {
		if (isValid(row, col)) {
			entities[row * cols + col] = e;
		}
	}

	public String toString(Function<T, AsciiRepresentation> mapper) {
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

	public void drawLine(int row1, int col1, int row2, int col2, T e) {
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

}
