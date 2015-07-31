package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.UserInterface;
import minesweeper.core.Field;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads line of text from the reader.
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
    @Override
	public void newGameStarted(Field field) {
        this.field = field;
//        do {
            update();
            processInput();
//            throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
//        } while(true);
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
    @Override
	public void update() {
		int r = 1;
		int c = Integer.toString(field.getColumnCount()).length()+1;
		int cislo = 0;
		char pismeno = 'A';
		char[] pismenoArray = new char[field.getRowCount()];
		
		
		for (int i = -1; i < field.getRowCount(); i++) {
			if (i == -1) {
				System.out.printf("%" + r + "s", " ");
				for (int j = -1; j < field.getColumnCount() - 1; j++) {
					System.out.printf("%" + c + "s", cislo);
					cislo++;
				}
			} else {
				for (int j = -1; j < field.getColumnCount(); j++) {
					if (j == -1) {
						System.out.printf("%" + r + "s", pismeno);
						pismeno++;
					} else {
						System.out.printf("%" + c + "s", field.getTile(i, j));
					}
				}
			}
			System.out.printf("%n");
		}
	}
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
//        throw new UnsupportedOperationException("Method processInput not yet implemented");
    }
}
