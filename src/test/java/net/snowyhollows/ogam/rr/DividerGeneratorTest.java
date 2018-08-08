package net.snowyhollows.ogam.rr;

import org.junit.Test;

/**
 * @author efildre
 */
public class DividerGeneratorTest {
	@Test
	public void shouldNotThrow() {
		DividerGenerator.SquareRoom room = new DividerGenerator.SquareRoom(0,25,0,80,-1,-1);
		room.divideIfBigEnough();

		CharBuffer2d charBuffer2d = new CharBuffer2d();
		charBuffer2d.fillWith('.');

		render(room, charBuffer2d);
	}

	void render(DividerGenerator.SquareRoom room, CharBuffer2d buffer) {
		char ch = '#';
		buffer.drawLine(room.top, room.left, room.top, room.right, ch);
		buffer.drawLine(room.bottom, room.left, room.bottom, room.right, ch);
		buffer.drawLine(room.top, room.left, room.bottom, room.left, ch);
		buffer.drawLine(room.top, room.right, room.bottom, room.right, ch);

		if (room.a != null) {
			render(room.a, buffer);
		}
		if (room.b != null) {
			render(room.b, buffer);
		}
		if (room.a != null && room.b != null) {
			boolean horizontal = room.a.right != room.right;

			if (horizontal) {
				buffer.put(room.top + room.roomsConnectedAt,room.a.right, '@');
			} else {
				buffer.put(room.a.bottom, room.left + room.roomsConnectedAt, '@');
			}

		}

	}
}
