package panels;

import settings.Settings;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SettingsPanel extends MasterPanel {

    JButton[] backgroundButtons;
    JButton[] playerColorButtons;
    JButton back;
    boolean goBack;
    backgroundEvent be = new backgroundEvent();
    playerEvent pe = new playerEvent();

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
            backgroundButtons[i].addActionListener(be);
            backgroundButtons[i].setBackground(Settings.getSettingsButtonColor());
            backgroundButtons[i].setMargin(new Insets(50, 50, 50, 50));
            add(backgroundButtons[i], gbc);
            gridx++;
        }
        backgroundButtons[0].setText("Boxes");
        backgroundButtons[1].setText("City");
        backgroundButtons[2].setText("Stripes");
        backgroundButtons[Settings.getBackground()].setEnabled(false);

        gridx = 0;
        gridy = 1;
        playerColorButtons = new JButton[3];
        for(int i = 0; i < playerColorButtons.length; i++){
            playerColorButtons[i] = new JButton();
            gbc.gridx = gridx;
            gbc.gridy = gridy;
            playerColorButtons[i].addActionListener(pe);
            playerColorButtons[i].setBackground(Settings.getSettingsButtonColor());
            playerColorButtons[i].setMargin(new Insets(50, 50, 50, 50));
            add(playerColorButtons[i], gbc);
            gridx++;
        }
        playerColorButtons[0].setText("Magenta");
        playerColorButtons[1].setText("Blue");
        playerColorButtons[2].setText("Green");
        playerColorButtons[0].setEnabled(false);

        back = new JButton("Back");
        goBack = false;
        back.setBackground(Settings.getSettingsButtonColor());
        back.setMargin(new Insets(50, 50, 50, 50));
        back.addActionListener(be);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(back, gbc);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    public void setBack() {
        goBack = false;
    }

    public boolean goBack() {
        return goBack;
    }

    public class backgroundEvent implements ActionListener {

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
    
    public class playerEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < backgroundButtons.length; i++){
                    if(playerColorButtons[i] == e.getSource()){
                        Settings.setPlayerColor(i);
                        playerColorButtons[i].setEnabled(false);
                    } else{
                        playerColorButtons[i].setEnabled(true);
                    }
                }
                repaint();
        }
    }
}
