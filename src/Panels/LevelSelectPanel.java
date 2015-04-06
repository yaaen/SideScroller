package Panels;

import Settings.Settings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LevelSelectPanel extends JPanel {

    int rows = 3;
    int cols = 8;
    JButton[][] levels = new JButton[rows][cols];
    JButton credits;
    JButton start;
    JButton settings;
    ExtrasEvent ee = new ExtrasEvent();
    int chosen = -1;
    ChosenEvent ce = new ChosenEvent();

    public LevelSelectPanel() {
        setFocusable(true);
        requestFocus();

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridbag);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(25, 25, 25, 25);
        gbc.weightx = 1;
        gbc.weighty = 1;
        int gridx = 0;
        int gridy = 0;

        //number of files in the Levels folder
        String dirString = "/Levels";
        Path dir = Paths.get(dirString);
        int numOfLevels = dir.getNameCount() + 1;

        int levelNum = 1;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                levels[r][c] = new JButton(String.valueOf(levelNum));
                levels[r][c].setFont(new Font("Arial", Font.PLAIN, 40));
                levels[r][c].addActionListener(ce);
                levels[r][c].setBackground(Color.MAGENTA);
                gbc.gridx = gridx;
                gbc.gridy = gridy;
                if(levelNum > numOfLevels || levelNum > Settings.getLevelsCompleted()){
                    if(levelNum == 1){
                        levels[r][c].setEnabled(true);
                    } else{
                        levels[r][c].setEnabled(false);
                    }
                } else{
                    levels[r][c].setEnabled(true);
                }
                this.add(levels[r][c], gbc);
                levelNum++;
                gridx++;
            }
            gridx = 0;
            gridy++;
        }

        credits = new JButton("Credits");
        credits.setFont(new Font("Arial", Font.PLAIN, 40));
        credits.setBackground(Color.MAGENTA);
        credits.addActionListener(ee);
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 2;
        this.add(credits, gbc);

        start = new JButton("Menu");
        start.setFont(new Font("Arial", Font.PLAIN, 40));
        start.setBackground(Color.MAGENTA);
        start.addActionListener(ee);
        gbc.gridx = 3;
        gbc.gridy = gridy;
        gbc.gridwidth = 2;
        this.add(start, gbc);

        settings = new JButton("Settings");
        settings.setFont(new Font("Arial", Font.PLAIN, 40));
        settings.setBackground(Color.MAGENTA);
        settings.addActionListener(ee);
        gbc.gridx = 6;
        gbc.gridy = gridy;
        gbc.gridwidth = 2;
        this.add(settings, gbc);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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

    public class ExtrasEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(credits == e.getSource()){
                chosen = 100;
            } else if(start == e.getSource()){
                chosen = 101;
            } else if(settings == e.getSource()){
                chosen = 102;
            }
        }
    }
}