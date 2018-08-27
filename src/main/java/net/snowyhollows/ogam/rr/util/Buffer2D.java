package net.snowyhollows.ogam.rr.util;

import net.bajobongo.beach.engine.Engine;

public class Buffer2D<T> {
	private final Engine<T> engine;


    public interface Buffer2DBrush {
		void stamp(int row, int col);
	}


	public Buffer2D(int rows, int cols, Engine<T> engine) {
		this.engine = engine;
	}

	public void fillWith(int rows, int cols, Buffer2DBrush brush) {
		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				brush.stamp(row, col);
			}
		}
	}

	public void stamp(int row, int col, Buffer2DBrush floorBrush) {
    	floorBrush.stamp(row, col);
	}

	public void drawLine(int row1, int col1, int row2, int col2, Buffer2DBrush brush) {
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
			brush.stamp((int)row, (int)col);
			row += stepRow;
			col += stepCol;
		}

	}

}
