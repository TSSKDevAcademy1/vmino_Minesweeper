package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.Minesweeper;
import minesweeper.UserInterface;
import minesweeper.core.Field;
import minesweeper.core.GameState;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
   
    private int time;
    
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
    	System.out.println("Vitaj "+System.getProperty("user.name")+" !!");
        this.field = field;
        update();
        do {
            processInput();
            update();

            if(field.getState() == GameState.SOLVED){
//            	update();
            	time = Minesweeper.getInstance().getPlayingSeconds();
            	Minesweeper.getInstance().addPlayerTime(System.getProperty("user.name"), time);
            	System.out.println("Gratulujem, vyhral si!");
            	System.out.println(Minesweeper.getInstance().getBestTimes().toString());
//            	try {
//					if (newGame(readLine())){
//					}
//					else{
//						System.exit(0);
//					}
//				} catch (WrongFormatException e) {
//					System.out.println(e.getMessage());
//				}
            	System.exit(0);
            }
            else if(field.getState() == GameState.FAILED){
            	System.out.println("Prehral si!");
//            	try {
//					if (newGame(readLine())){
//						newGameStarted(field);
//					}
//					else{
//						System.exit(0);
//					}
//				} catch (WrongFormatException e) {
//					System.out.println(e.getMessage());
//				}
            	System.exit(0);
            }
        } while(true);
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
    @Override
	public void update() {
    	StringBuilder builder = new StringBuilder();
    	Formatter formatter = new Formatter(builder);
		int r = 1;
		int c = Integer.toString(field.getColumnCount()).length()+1;
		int cislo = 0;
		char pismeno = 'A';
		
		for (int i = -1; i < field.getRowCount(); i++) {
			if (i == -1) {
				formatter.format("%" + r + "s", " ");
				for (int j = -1; j < field.getColumnCount() - 1; j++) {
					formatter.format("%"+c+"s", cislo);
					cislo++;
				}
			} else {
				for (int j = -1; j < field.getColumnCount(); j++) {
					if (j == -1) {
						formatter.format("%"+r+"s", pismeno);
						pismeno++;
						if (pismeno>'Z'){
							pismeno = 'A';
						}
					} else {
						formatter.format("%"+c+"s", field.getTile(i, j));
					}
				}
			}
			formatter.format("%n");
		}
	formatter.format("%27s", "Pocet ostavajucich min: "+field.getRemainingMineCount());
	formatter.format(" %3s", Minesweeper.getInstance().getPlayingSeconds());
	
	formatter.close();
	System.out.println(builder);	
	}
    
	private String getLetter(int row) {
		int ch;
		String result = "";
		if(row/26 == 0){
			ch = row%26;
			result+=Character.toChars(ch);
		}
		else{
			ch=row/26;
			result+=Character.toChars(ch);
			ch=row%26;
			result+=Character.toChars(ch);
		}
		return result;
	}
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
    	System.out.println("X – ukoncenie hry");
    	System.out.println("MA1 - oznacenie dlazdice v riadku A a stlpci 1");
    	System.out.println("OB4 – odkrytie dlazdice v riadku B a stlpci 4.");
    	
    	String input=readLine();
    	try{
    		handleInput(input);
    	}
    	catch (WrongFormatException e){
    		e.printStackTrace();
    		System.err.println(e.getMessage());
    	}
    }

    private void handleInput(String input) throws WrongFormatException {
//    	System.out.printf("%3s", input);
    	input = input.toUpperCase();
//    	X|((M|O)([A-Z])([0-9]+))
    	Pattern pattern = Pattern.compile("[OM]([A-Z])([0-9]+)");
    	Matcher matcher = pattern.matcher(input);
    	if(matcher.matches()){
    		char x = matcher.group(0).charAt(0);
    		int row = matcher.group(1).charAt(0)-'A';
    		int column = Integer.parseInt(matcher.group(2));
    		if (x=='O' && row <= field.getRowCount() && column <= field.getColumnCount()){
    			field.openTile(row, column);
    		}
    		else if (x=='M' && row <= field.getRowCount() && column <= field.getColumnCount()){
        		field.markTile(row, column);
        	}
    	}
    	else if(input.length()>0 && input.charAt(0)=='X' && input.length()<2){
    		System.err.println("Hra bola ukoncena");
    			System.exit(0);
    	}
    	else {
    		throw new WrongFormatException("Zly format");
    	}
    }
    
//    private boolean newGame(String s) throws WrongFormatException {
//    	System.out.println("Hrat znova ? (A/N)");
//    	char ch = s.charAt(0);
//    	if(ch == 'A'){
//    		return true;
//    	}
//    	else if(ch == 'N'){
//    		return false;
//    	}
//    	else {
//    		throw new WrongFormatException("Nespravny format");
//    	}
//    }
    
}
