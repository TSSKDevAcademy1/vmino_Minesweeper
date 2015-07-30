package minesweeper.core;

import minesweeper.core.Tile.State;

/**
 * Mine tile.
 */
public class Mine extends Tile {
	/**
	 * returns mine as char *
	 */
	@Override
	public String toString() {
		if (this.getState() == State.OPEN) {
			return "*";
		} else {
			return super.toString();
		}
	}
}
