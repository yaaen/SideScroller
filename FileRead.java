
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileRead {

    	private String[][] levelArray;

	/**
	 * This constructor takes no parameters and simply creates the
	 * FileRead class. setLevelArray() must be called after this to
	 * setup the levelArray
	 */ 
	public FileRead(){
		
	}

	/**
	 * Takes in an int, level, and from this sets levelArray using 
	 * the data read in from the text file
	 * 
	 * @param level	the level from which to set the Array 
	 */
	public void setLevelArray(int level){
		this.levelArray = readLevelFromFile(level);
	}
	
	/**
	 * This method returns a String[][] that contains the letters from the
	 * text file that was last read in
	 * 
	 * @return	String[][] containing the most recently read in level
	 */
    	public String[][] getLevelArray() {
        	return this.levelArray;
    	}

	/**
	 * Takes a given level and reads from its corresponding text file and puts 
	 * the letters into a string array to be parsed later on by another method
	 * 
	 * @param level	the level that is to be read and placed into the String array
	 * @return	String[][] that contains the level specified
	 */
	private String[][] readLevelFromFile(int level) {
		//create a file to read in the level and add to a string
		File file = new File("Levels/Level" + level + ".txt"); //this might need ".txt" cant try out right now
		String levelString = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			levelString = sb.toString();
			br.close();
		} catch (Exception e) {
			System.out.print(e.toString());
		}

        	//split levelString to an array that contains every line
        	String[] array = levelString.split("\n");
        	int length = array[0].length();
        	int height = array.length;
        	String[][] levelArray = new String[height][length];

        	//split every line by every characters
		for (int i = 0; i < height; i++) {
			levelArray[i] = array[i].split("");
		}

		return levelArray;
	}
}
