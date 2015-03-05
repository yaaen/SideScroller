public class FileReader{
  
  File file;
  
  public FileReader(){
    
  }
  
  public void readLevelFromFile(int level){
      //EVERYTHING HERE NEEDS TO BE IMPORTED
      //i programmed in the browser so idk the imports
      //WILL NOT BE VOID LATER ON
      
      //create a file to read in the level and add to a string
      file = new File("level" + level);
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
      
      //convert the positions to a more readable form
      //convert to an array
      //return that
  }
}
