import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LevelSelectPanel extends JPanel {

    int rows = 3;
    int cols = 8;
    JButton[][] levels = new JButton[rows][cols];
    private int chosen = -1;
    ChosenEvent e = new ChosenEvent();

    public LevelSelectPanel() {
        setBorder(new EmptyBorder(40, 40, 40, 40));
        setSize(1600, 1000);
        setLayout(new GridLayout(4, 0, 40, 40));

        //number of files in the Levels folder
        String dirString = "/Levels";
        Path dir = Paths.get(dirString); // ist this supposed to be paths (plural?)
        int numOfLevels = dir.getNameCount() + 1;

        int levelNum = 1;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                levels[r][c] = new JButton(String.valueOf(levelNum));
                levels[r][c].setFont(new Font("Arial", Font.Plain, 40));
                levels[r][c].setMargin(new Insets(50, 50, 50, 50));
                levels[r][c].addActionListener(e);
                levels[r][c].setBackground(Color.MAGENTA);
                if(levelNum <= numOfLevels){
                    levels[r][c].setEnabled(true);
                } else{
                    levels[r][c].setEnabled(false);
                }
                this.add(levels[r][c]);
                levelNum++;
            }
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 470, 320);
        g.setColor(Color.RED);
        g.fillRect(100, 700, 200, 190);
        g.setColor(Color.GREEN);
        g.fillRect(1200, 100, 300, 700);
        
        g.setColor(Color.BLUE);
        g.fillRect(1050, 600, 270, 320);
        g.setColor(Color.RED);
        g.fillRect(700, 50, 290, 100);
        g.setColor(Color.GREEN);
        g.fillRect(250, 600, 300, 200);
    }
    
    public void setChosen(int chosen) {
        this.chosen = chosen;
    }
    
    public int getChosen() {
        return chosen;
    }

    public class ChosenEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loop:
            for(int r = 0; r < rows; r++){
                for(int c = 0; c < cols; c++){
                    if(levels[r][c] == e.getSource()){
                        chosen = Integer.parseInt(levels[r][c].getText());
                        break loop;
                    }
                }
            }
        }
    }
}
