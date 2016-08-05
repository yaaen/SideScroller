package tools;

import settings.Settings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileRead {

    private String[][] levelArray;
    private double[] bestLevelTimes;

    /**
     * This constructor takes no parameters and simply creates the
     * FileRead class. setLevelArray() must be called after this to
     * setup the levelArray
     */
    public FileRead() {
    }

    /**
     * Takes in an init, level, and from this sets levelArray using 
     * the data read in from the text file
     * 
     * @param level	the level from which to set the Array 
     */
    public void setLevelArray(int level) {
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
        
        File file = new File("/Users/home/git/SideScroller/levels/level" + level + ".txt");
        String levelString = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null){
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
        for(int i = 0; i < height; i++){
            levelArray[i] = array[i].split("");
        }

        return levelArray;
    }

    /**
     * This method returns a string file containing the text that makes up settings.txt
     * 
     * @return  String containing text from settings.txt
     */
    public String getSettings() {
        File file = new File("/Users/home/git/SideScroller/settings/settings.txt");
        String levelString = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            levelString = sb.toString();
            br.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return levelString;
    }

    /**
     * Called when a new level is beaten. Adds one to levelscompleted within settings.txt.
     */
    public void updateSettingsWithNewLevel() {
        //up levels completed by one
        BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File("/Users/home/git/SideScroller/settings/settings.txt");
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("gameheight="+Settings.getGameHeight()+"\n"
                    + "gamewidth="+Settings.getGameWidth()+"\n"
                    + "levelscompleted="+(Settings.getLevelsCompleted()+1)+"\n"
                    + "background="+Settings.getBackground()+"\n"
                    + "movementspeed="+Settings.getMovementSpeed()+"\n"
                    + "gravity="+Settings.getGravity()+"\n"
                    + "blocksize="+Settings.getBlockSize()+"=");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void setBestLevelTimes(){
        //TODO
    }
    
    /**
     * Returns an array of doubles with the best level times from each level.
     * 
     * return double[] containing best level times
     */
    public double[] getBestLevelTimes(){
        return bestLevelTimes;
    }
    
    /**
     * Return the total amount of levels within the levels folders
     * @return int representing total levels
     */
    public int getTotalLevels(){
        return new File("/Users/home/git/SideScroller/levels").listFiles().length;
    }
    
    /**
     * Updates (possibly) the best time the level was completed in only if it is better than the previous best time.
     * 
     * @param   level   the level completed
     * @param   time    time in seconds the level was completed in
     */
    public void beatLevel(int level, double time){
        if(bestLevelTimes[level] > time){
            bestLevelTimes[level] = time;
            //TODO:
            //put new level into the text file
        }
    }
}
