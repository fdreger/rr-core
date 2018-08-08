package net.snowyhollows.ogam.rr;/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */

import java.util.Arrays;

/**
 * @author efildre
 */
public final class CharBuffer2d {
	private final int rows = 100;
	private final int cols = 100;
	private final char[] chars = new char[rows * cols];

	public void fillWith(char ch) {
		Arrays.fill(chars, ch);
	}

	public boolean isValid(int row, int col) {
		return (row < rows && row >= 0 && col < cols && col >= 0);
	}

	public char get(int row, int col) {
		if (isValid(row, col)) {
			return chars[row * cols + col];
		} else return ' ';
	}

	public void put(int row, int col, char ch) {
		if (isValid(row, col)) {
			chars[row * cols + col] = ch;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			sb.append(chars, i * cols, cols);
			sb.append("\n");
		}
		return sb.toString();
	}

	public void drawLine(int row1, int col1, int row2, int col2, char ch) {
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
			put((int)row, (int)col, ch);
			row += stepRow;
			col += stepCol;
		}

	}
}
