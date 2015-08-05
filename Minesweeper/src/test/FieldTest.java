package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import minesweeper.core.*;

public class FieldTest {

	static final int ROWS = 9;
	static final int COLUMNS = 9;
	static final int MINES = 10;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void isSolved() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		assertEquals(GameState.PLAYING, field.getState());

		int open = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					field.openTile(row, column);
					open++;
				}
				if (field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
					assertEquals(GameState.SOLVED, field.getState());
				} else {
					assertNotSame(GameState.FAILED, field.getState());
				}
			}
		}

		assertEquals(GameState.SOLVED, field.getState());
	}

	@Test
	public void generate() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		int mineCount = 0;
		int clueCount = 0;

		assertEquals(ROWS, field.getRowCount());
		assertEquals(COLUMNS, field.getColumnCount());
		assertEquals(MINES, field.getMineCount());
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				assertNotNull(field.getTile(row, column));

				if (field.getTile(row, column) instanceof Clue) {
					clueCount++;
				}
				else if (field.getTile(row, column) instanceof Mine) {
					mineCount++;
				}
			}
		}
		assertEquals(MINES, mineCount);
		assertEquals(ROWS * COLUMNS - MINES, clueCount);

	}
	
	@Test
	public void openTile() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				
				field.openTile(row, column);
				assertEquals(Tile.State.OPEN, tile.getState());
				
				field.openTile(row, column);
				assertEquals(Tile.State.OPEN, tile.getState());
				
				field.markTile(row, column);
				assertEquals(Tile.State.OPEN, tile.getState());
			}
		}

	}
	
	@Test
	public void openAdjacentTiles() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		
	}
	
	@Test
	public void markTile() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				
				field.markTile(row, column);
				assertEquals(Tile.State.MARKED, tile.getState());
				
				field.openTile(row, column);
				assertEquals(Tile.State.MARKED, tile.getState());
				
				field.markTile(row, column);
				assertEquals(Tile.State.CLOSED, tile.getState());
				
				field.markTile(row, column);
				assertEquals(Tile.State.MARKED, tile.getState());
			}
		}
	}
	
	
	@Test
	public void countAdjacentMines() {
		Field field = new Field(ROWS, COLUMNS, MINES);
	}
	
}
