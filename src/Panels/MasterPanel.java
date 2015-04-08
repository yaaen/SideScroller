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
        System.out.println(Settings.getBackground());
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
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            g.setColor(Color.WHITE);
            for(int i = 0; i < 30; i++){
                int x = (int)(Math.random() * this.getWidth());
                int y = (int)(Math.random() * this.getHeight());
                g.fillRect(x, y, 10, 10);
            }
            
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(150, 350, 350, 650);
            g.fillRect(600, 550, 350, 450);
            g.fillRect(1100, 200, 350, 800);
            
            g.setColor(Color.WHITE);
            int space = 20;
            int block = 26;
            for(int r = 0; r < 7; r++){
                for(int c = 0; c < 13; c++){
                   g.fillRect(180 + (space * (r+1)) + (block * (r-1)), 380 + (space * (c+1)) + (block * (c-1)) + (5 * c), block, block + 5);
                }
            }
            for(int r = 0; r < 7; r++){
                for(int c = 0; c < 10; c++){
                    g.fillRect(630 + (space * (r+1)) + (block * (r-1)), 580 + (space * (c+1)) + (block * (c-1)) + (5 * c), block, block + 5);
                }
            }
            for(int r = 0; r < 7; r++){
                for(int c = 0; c < 17; c++){
                    g.fillRect(1130 + (space * (r+1)) + (block * (r-1)), 230 + (space * (c+1)) + (block * (c-1)) + (5 * c), block, block + 5);
                }
            }
        } else if(Settings.getBackground() == 2){
        } else{
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
