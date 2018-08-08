package net.snowyhollows.ogam.rr;

import java.util.Random;

/**
 * @author efildre
 */
public class DividerGenerator {

	private static final Random random = new Random();

	public static class SquareRoom {
		int top, bottom, left, right;
		int reservedRow;
		int reservedCol;

		public SquareRoom(int top, int bottom, int left, int right, int reservedRow, int horizontalConnection) {
			this.top = top;
			this.bottom = bottom;
			this.left = left;
			this.right = right;
			this.reservedRow = reservedRow;
			this.reservedCol = horizontalConnection;
		}

		SquareRoom a;
		SquareRoom b;
		int roomsConnectedAt;

		public void divideIfBigEnough() {
			int width = right - left;
			int height = bottom - top;
			boolean canH = width > 5 && height > 3;
			boolean canV = height > 5 && width > 3;

			if (canH && !canV) {
				divideH();
			} else if (!canH && canV) {
				divideV();
			} else if (canH && canV) {
				if (random.nextBoolean()) {
					divideV();
				} else {
					divideH();
				}
			}

			if (a != null){
				a.divideIfBigEnough();
				b.divideIfBigEnough();
			}
		}


		public void divideH() {
			int width = right - left;
			int height = bottom - top;
			int slice = random.nextInt(width - 4) + 2;

			if (slice == reservedCol) {
				slice++;
			}
			roomsConnectedAt = random.nextInt(height - 3) + 2;
			a = new SquareRoom(top, bottom, left, left + slice, roomsConnectedAt, -1);
			b = new SquareRoom(top, bottom, left + slice, right, roomsConnectedAt, -1);
		}

		public void divideV() {
			int width = right - left;
			int height = bottom - top;
			int slice = random.nextInt(height - 4) + 2;

			if (slice == reservedRow) {
				slice++;
			}
			roomsConnectedAt = random.nextInt(width - 3) + 2;
			a = new SquareRoom(top, top + slice, left, right, -1, roomsConnectedAt);
			b = new SquareRoom(top + slice, bottom, left, right, -1, roomsConnectedAt);
		}

	}





}
