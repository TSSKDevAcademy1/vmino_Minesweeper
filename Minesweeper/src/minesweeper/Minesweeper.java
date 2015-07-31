package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
    /** User interface. */
    private UserInterface userInterface;
 
    /**
     * Constructor.
     */
    private Minesweeper() {
        userInterface = new ConsoleUI();
        
        Field field = new Field(15, 15, 10);
        field.openTile(5,5);
        field.markTile(1,1);
//        field.markTile(1,1);
        field.markTile(5,5);
//        for (int i = 0; i < 6; i++) {
//			for (int j = 0; j < 6; j++) {
//				field.openTile(i,j);
//			}
//        }
        
        userInterface.newGameStarted(field);
        System.out.println(field.isSolved());

//        System.out.println(field);
        
    }

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper();
        
    }
}
