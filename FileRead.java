
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileRead{ 
  
  public FileRead(){
    
  }
  
  public String[][] readLevelFromFile(int level){
      
      //create a file to read in the level and add to a string
      File file = new File("level" + level); //this might need ".txt" cant try out right now
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
          } catch(Exception e){
              System.out.print(e.toString());
          } finally {
              br.close();
          }
      
      //make an arraylist to contain all the positions
      ArrayList<String> stringList = new ArrayList<String>();
      for(int i = 0; i < levelString; i++){
          stringList.add(levelString.substring(i, i + 1);
      }
      
      //why not have the file it self have the height and width as the first couple chars
      //bc it might get confusing adding to it then or getting rid of things
      
      //n will signify new line so that how's we can see how long the level is
      int length = 0;
      for(int i = 0; i < levelString.size(); i++){
        //in here will use stringList to count how chars until the first n
        //and thats how we will know how big the level is wide
        if(stringList.get(i).equalsIgnoreCase("n"){
          break;
        } else {
          length++;
        }
      }
      
      //count height of the level
      int height = 0;
      for(int i = 0; i < levelString.size(); i++){
        if(stringList.get(i).equalsIgnoreCase("n")){
          height++;
        }
      }
      
      //convert to a String array using the height and length variables
      String[][] levelArray = new String[height][length];
        
      //add in the letters from stringList
      //not sure whether to go row column or column row
        
        
      return levelArray;
  }
}
