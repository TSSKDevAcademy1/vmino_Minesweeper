package minesweeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
    private BestTimes bestTimes;
    private Settings settings;
    private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator")
	+ "minesweeper.settings";
 
    /**
     * Constructor.
     */
    private Minesweeper() {
    	instance = this;
    	
        userInterface = new ConsoleUI();
        bestTimes = new BestTimes();
    	bestTimes.addPlayerTime("Vlado", 5);
    	bestTimes.addPlayerTime("Kubo", 6);
    	bestTimes.addPlayerTime("Dakto", 1);

       	Settings settings = getSetting();
  
        Field field = new Field(settings.getRowCount(), settings.getColumnCount(), settings.getMineCount());
        System.out.println(bestTimes.toString());
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
    
    public BestTimes getBestTimes(){
    	return bestTimes;
    }
    
    public Settings getSetting(){
		return Settings.load();
    }
    
    public void setSetting() {
    	this.settings.save();
    }
}
