
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileRead {

    private String[][] levelArray;

    //pr0ves
    //made this constructor so that everytime you create a FileRead object it creates automatically the level array
    public FileRead(int level) {
       this.levelArray = readLevelFromFile(level);
    }


    //pr0ves
    //i think you could make this method in a better way, for example you can jump the arraylist part and convert directly levelString to levelArray
    //also you could delete the "n" in the level file, because every line is terminated by "\n" by default

	private String[][] readLevelFromFile(int level) {

		//create a file to read in the level and add to a string
		File file = new File("Levels/level" + level + ".txt"); //this might need ".txt" cant try out right now
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

    public String[][] getLevelArray() {
        return this.levelArray;
    }
}
