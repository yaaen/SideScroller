public class FileReader{
  
  File file;
  
  public FileReader(){
    
  }
  
  public void readLevelFromFile(int level){
      //EVERYTHING HERE NEEDS TO BE IMPORTED
      //i programmed in the browser so idk the imports
      //WILL NOT BE VOID LATER ON
      file = new File("level" + level);
      String everything;
      BufferedReader br = new BufferedReader(new FileReader(file));
          try {
              StringBuilder sb = new StringBuilder();
              String line = br.readLine();

              while (line != null) {
                  sb.append(line);
                  sb.append(System.lineSeparator());
                  line = br.readLine();
              }
              everything = sb.toString();
    } catch(Exception e){
      System.out.print(e.toString());
    } finally {
        br.close();
    }
      //make an arraylist to contain all the positions
      //convert the positions to a more readable form
      //convert to an array
      //return that
  }
}
