package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	private long startMillis;
	private  static Minesweeper instance;
    /** User interface. */
    private UserInterface userInterface;
 
    /**
     * Constructor.
     */
    private Minesweeper() {
    	instance = this;
    	
        userInterface = new ConsoleUI();
        
        Field field = new Field(9, 20, 15);
        
        startMillis = System.currentTimeMillis();
        userInterface.newGameStarted(field);      
    }

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper();
        
    }
    
    public static Minesweeper getInstance(){
    	return instance;
    }
    
    public int getPlayingSeconds(){
    	int result;
    	result=(int) (System.currentTimeMillis() - startMillis);
    	result/=1000;
    	return result;
    }
}
