package Panels;

import Settings.Settings;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel {

    JButton[] backgroundButtons;
    event e = new event();
    JButton back;
    boolean goBack;

    public SettingsPanel() {
        setFocusable(true);
        requestFocus();

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridbag);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(50, 50, 50, 50);
        gbc.weightx = 1;
        gbc.weighty = 1;
        int gridx = 0;
        int gridy = 0;

        backgroundButtons = new JButton[3];
        for(int i = 0; i < backgroundButtons.length; i++){
            backgroundButtons[i] = new JButton();
            gbc.gridx = gridx;
            gbc.gridy = gridy;
            backgroundButtons[i].addActionListener(e);
            backgroundButtons[i].setBackground(Color.MAGENTA);
            backgroundButtons[i].setMargin(new Insets(50, 50, 50, 50));
            add(backgroundButtons[i], gbc);
            gridx++;
        }
        backgroundButtons[0].setText("Boxes");
        backgroundButtons[1].setText("City");
        backgroundButtons[2].setText("Mountains");
        backgroundButtons[Settings.getBackground()].setEnabled(false);

        back = new JButton("Back");
        goBack = false;
        back.setBackground(Color.MAGENTA);
        back.setMargin(new Insets(50, 50, 50, 50));
        back.addActionListener(e);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(back, gbc);
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
    
    public void setBack() {
        goBack = false;
    }

    public boolean goBack() {
        return goBack;
    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(back == e.getSource()){
                goBack = true;
            } else{
                for(int i = 0; i < backgroundButtons.length; i++){
                    if(backgroundButtons[i] == e.getSource()){
                        Settings.setBackground(i);
                        backgroundButtons[i].setEnabled(false);
                    } else{
                        backgroundButtons[i].setEnabled(true);
                    }
                }
                repaint();
            }
        }
    }
}
