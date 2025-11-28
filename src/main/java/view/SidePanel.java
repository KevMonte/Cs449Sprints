package view;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    public JLabel playerLabel;
     public JLabel playerTypeLabel;
    public JLabel scoreLabel;
    int score=0;


    public JRadioButton sButton;
    public JRadioButton oButton;
    public ButtonGroup soButtonGroup;

    public JRadioButton computerButton;
    public JRadioButton humanButton;
    public ButtonGroup playerButtonGroup;




    public SidePanel(String playerColor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());

        playerLabel = new JLabel(playerColor);    
        add(playerLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        sButton = new JRadioButton("S");
        oButton = new JRadioButton("O");
        soButtonGroup = new ButtonGroup();

        soButtonGroup.add(sButton);
        soButtonGroup.add(oButton);

        add(sButton);
        add(oButton);
        add(Box.createRigidArea(new Dimension(0, 20)));

        computerButton = new JRadioButton("Computer");
        humanButton = new JRadioButton("Human", true);// both players are going to be assumed to be human at first
        playerButtonGroup = new ButtonGroup(); 
        playerTypeLabel= new JLabel("Type:");
        add(playerTypeLabel);

        playerButtonGroup.add(computerButton);
        playerButtonGroup.add(humanButton);
       
        add(computerButton);
        add(humanButton);



        scoreLabel = new JLabel("Score:" + score);
        add(scoreLabel);

        add(Box.createVerticalGlue());
       
    }
    public void setScore(int score) {
       this.score = score;
        scoreLabel.setText("Score:" + score);
    }
    public int getScore() {
        return score;
    }
}
