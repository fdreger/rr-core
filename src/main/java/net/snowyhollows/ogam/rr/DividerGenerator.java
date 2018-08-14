package net.snowyhollows.ogam.rr;

import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.util.ObjectArray2D;

import java.util.Random;
import java.util.function.Supplier;

public class DividerGenerator {

	private static final Random random = new Random();
	private Entity wall;
	private Entity nothing;

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

			int whatNow = random.nextInt(5);
			if (whatNow == 0) {
				return;
			} else if (a != null){
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

	private SquareRoom baseRoom;

	@WithFactory
	public DividerGenerator(@ByName("level.height") int levelRows,
							@ByName("level.width") int levelCols,
							@ByName("entity.wall") Entity wall,
							@ByName("entity.nothing") Entity nothing) {
		this.wall = wall;
		this.nothing = nothing;
		this.baseRoom = new SquareRoom(0, levelRows, 0, levelCols, -1, -1);
	}

	public void render(ObjectArray2D buffer, Supplier<Entity> doorSupplier) {
		baseRoom.divideIfBigEnough();
		render(baseRoom, buffer, doorSupplier);
	}

	private void render(DividerGenerator.SquareRoom room, ObjectArray2D buffer, Supplier<Entity> doorSupplier) {
		buffer.drawLine(room.top, room.left, room.top, room.right, wall);
		buffer.drawLine(room.bottom, room.left, room.bottom, room.right, wall);
		buffer.drawLine(room.top, room.left, room.bottom, room.left, wall);
		buffer.drawLine(room.top, room.right, room.bottom, room.right, wall);

		if (room.a != null) {
			render(room.a, buffer, doorSupplier);
		}
		if (room.b != null) {
			render(room.b, buffer, doorSupplier);
		}
		if (room.a != null && room.b != null) {
			boolean horizontal = room.a.right != room.right;

			if (horizontal) {
				buffer.put(room.top + room.roomsConnectedAt,room.a.right, nothing);
				Entity door = doorSupplier.get();
				door.position.setCoords(new Coords(room.top + room.roomsConnectedAt, room.a.right));
			} else {
				buffer.put(room.a.bottom, room.left + room.roomsConnectedAt, nothing);
                Entity door = doorSupplier.get();
                door.position.setCoords(new Coords(room.a.bottom, room.left + room.roomsConnectedAt));
			}
		}
	}
}