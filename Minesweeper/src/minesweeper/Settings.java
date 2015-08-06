package minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {
	private final int rowCount;
	private final int columnCount;
	private final int mineCount;
	public static final Settings beginner = new Settings(9,9,10);
	public static final Settings intermediate = new Settings(16,16,40);
	public static final Settings expert = new Settings(16,30,99);
	private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + "minesweeper.settings";
	/**
	 * 
	 */
	private static final long serialVersionUID = 6902605351560444645L;

	public Settings(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}
	
	public boolean equals( Object o){
		return(this.hashCode() == o.hashCode());
	}
	
	public int hashCode() {
		return this.rowCount * this.columnCount * this.mineCount;
	}
	
	public void save() throws IOException{
		try(FileOutputStream file = new FileOutputStream(SETTING_FILE);
			ObjectOutputStream object = new ObjectOutputStream(file);){
		object.writeObject(this);
		}
		catch(IOException e){
			System.err.println(e.getMessage());
		}

	}
	public static Settings load() throws IOException, ClassNotFoundException{
		
		FileInputStream file = new FileInputStream(SETTING_FILE);
		ObjectInputStream object = new ObjectInputStream(file);
		Object obj = object.readObject();

		if(obj.equals(intermediate)){
			return intermediate;
		}
		else if(obj.equals(expert)){
			return expert;
		}
		else{
			return beginner;
		}
		
	}
}
