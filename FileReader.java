public class FileReader{
  
  public FileReader(){
    
  }
  
  public String[][] readLevelFromFile(int level){
      //EVERYTHING HERE NEEDS TO BE IMPORTED
      //i programmed in the browser so idk the imports
      //WILL NOT BE VOID LATER ON
      
      //create a file to read in the level and add to a string
      File file = new File("level" + level);
      String levelString;
      BufferedReader br = new BufferedReader(new FileReader(file));
          try {
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
        if(levelString.get(i).equalsIgnoreCase("n")){
          height++;
        }
      }
      
      //convert to a String array using the height and length variables
      
  }
}
