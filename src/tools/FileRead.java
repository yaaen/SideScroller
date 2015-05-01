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
        
        //TODO find away so that its not user specific
        //Its getting NoFileException and cannot compile
        File file = new File("src\\Levels\\level" + level + ".txt");
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

    public String getSettings() {
        //CHANGE THIS TO WHEREEVER YOURS IS:
        File file = new File("C:\\Users\\Derek\\Documents\\NetBeansProjects\\Major\\SideScroller\\src\\Settings\\settings.txt");
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

    public void updateSettingsWithNewLevel() {
        //up levels completed by one
        BufferedWriter writer = null;
        try {
            //create a temporary file
            //CHANGE THIS FILE PATH TO BE THE EXACT PATH WHERE YOUR FILE IS LOCATED:
            File logFile = new File("C:\\Users\\Derek\\Documents\\NetBeansProjects\\Major\\SideScroller\\src\\Settings\\settings.txt");
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
    
    //in seconds
    public double[] getBestLevelTimes(){
        return bestLevelTimes;
    }
    
    public int getTotalLevels(){
        return new File(\\src\\levels).listFiles().length
    }
    
    //time is in seconds
    public void beatLevel(int level, double time){
        if(bestLevelTimes[level] > time){
            bestLevelTimes[level] = time;
            //TODO:
            //put new level into the text file
        }
    }
}
