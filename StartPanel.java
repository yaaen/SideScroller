import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
  
  int chosen = -1;
  
  public StartPanel(){
     setSize(1600, 1000);
        setLayout(null);
        
        int count = 1;
        int yValue = 150;
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 8; c++){
                levels[r][c] = new JButton(String.valueOf(count)); //Array levels is never defined
                levels[r][c].setLocation(c*190 + 80, yValue);
                levels[r][c].setSize(100, 100);
                this.add(levels[r][c]);
                count++;
            }
            yValue += 200;
        }
  }
  
  @Override
  public void paint(Graphics g){
      //TODO:
      //paint the background of the start screen
  }
  
  public int chosen(){
    return chosen;
  }
  
  public class event implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int rr = 0, cc = 0;
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 8; c++){
                    if(levels[r][c] == e.getSource()){
                        chosen = Integer.parseInt(levels[r][c].getText());
                        break;
                    }
                }
            }
        }
    }
}
