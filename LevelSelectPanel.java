
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

    JButton[][] levels = new JButton[3][8];
    private int chosen = -1;
    ChosenEvent e = new ChosenEvent();

    public LevelSelectPanel() {
        setBorder(new EmptyBorder(40, 40, 40, 40));
        setSize(1600, 1000);
        setLayout(new GridLayout(0, 8, 40, 40));

        //number of files in the Levels folder
        String dirString = "/Levels";
        Path dir = Paths.get(dirString); // ist this supposed to be paths (plural?)
        int numOfLevels = dir.getNameCount();

        int levelNum = 1;
        int yValue = 150;
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 8; c++){
                levels[r][c] = new JButton(String.valueOf(levelNum));
                levels[r][c].setMargin(new Insets(50, 50, 50, 50));
                levels[r][c].addActionListener(e);
                if(levelNum <= numOfLevels){
                    levels[r][c].setEnabled(false);
                }
                this.add(levels[r][c]);
                levelNum++;
            }
            yValue += 200;
        }
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
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 8; c++){
                    if(levels[r][c] == e.getSource()){
                        chosen = Integer.parseInt(levels[r][c].getText());
                        break loop;
                    }
                }
            }
        }
    }
}
