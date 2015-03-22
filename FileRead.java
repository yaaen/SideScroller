
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileRead {

	public FileRead() {

	}

	public String[][] readLevelFromFile(int level) {

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

		//make an arraylist to contain all the positions
		ArrayList<String> stringList = new ArrayList<String>();
		for (int i = 0; i < levelString.length(); i++) {
			stringList.add(levelString.substring(i, i + 1));
		}

		//why not have the file it self have the height and width as the first couple chars
		//bc it might get confusing adding to it then or getting rid of things

		//n will signify new line so that how's we can see how long the level is
		int length = 0;

        //pr0ves: replaced for with a foreach, and also modified the if statement so that you don't have to use the break
        for (String s : stringList) {
            //in here will use stringList to count how chars until the first n
            //and thats how we will know how big the level is wide
            if (!(s.equalsIgnoreCase("n"))) {
                length++;
            }
        }

		//count height of the level
		int height = 0;

        for (String s : stringList) {
            if (s.equalsIgnoreCase("n")) {
                height++;
            }
        }

		//should this be height length or the other way around?
		//convert to a String array using the height and length variables
		String[][] levelArray = new String[height][length];

        for (int i = 0; i < stringList.size(); i++) {
            //pr0ves
            //you don't need multiple, empty if statements
            //this if is needed to work only when the string is not equal to "s" or "g" are false so you can use
            //NOT(A OR B) or A NOR B, where A is true if the string equals "s" and where B is true when it equals "g"

            if (!(stringList.get(i).equalsIgnoreCase("s")||stringList.get(i).equalsIgnoreCase("g"))) {
                stringList.remove(i);
            }
        }

		//add in the letters from stringList
		//not sure whether to go row column or column row\
		//this might work but probably not, the i*length
		//is because its currently in an arraylist.
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				levelArray[i][j] = stringList.get(j + (i * length) + i);
			}
		}

		return levelArray;
	}
}
