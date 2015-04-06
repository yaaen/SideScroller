package Panels;

import Settings.Settings;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Derek
 */
public class MasterPanel extends JPanel {

    public MasterPanel() {
    }

    public void paintComponent(Graphics g) {
        if(Settings.getBackground() == 0){
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
        } else if(Settings.getBackground() == 1){
        } else if(Settings.getBackground() == 2){
        } else{
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
