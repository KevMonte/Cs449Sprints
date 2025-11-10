package view;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public SidePanel leftPanel;
    public SidePanel rightPanel;
    public BoardPanel boardPanel;
    public JPanel boardSizeLimiter;
    public TopPanel topPanel;
    public BottomPanel bottomPanel;

    public int turn = 0; // 0 is red player turn, 1 is blue red player goes first, at least for now

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        
        BorderLayout borderLayoutManager = new BorderLayout();

        setTitle("SOS Game");
        setSize(800, 600);
        mainPanel.setLayout(borderLayoutManager);
        leftPanel = new SidePanel("Red Player");
        leftPanel.playerLabel.setForeground(Color.RED);
       
        rightPanel = new SidePanel("Blue Player");
         rightPanel.playerLabel.setForeground(Color.BLUE);
        boardPanel = new BoardPanel(3);
        boardSizeLimiter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel = new TopPanel();
        bottomPanel = new BottomPanel();

        boardPanel.setPreferredSize(new Dimension(500,500));

        boardSizeLimiter.add(boardPanel);
        boardSizeLimiter.add(Box.createVerticalGlue());

        add(Box.createRigidArea(new Dimension(25, 0)), borderLayoutManager.WEST);
        add(Box.createRigidArea(new Dimension(25, 0)), borderLayoutManager.EAST);

        mainPanel.add(leftPanel, borderLayoutManager.WEST);
        leftPanel.sButton.setSelected(true);
        mainPanel.add(rightPanel, borderLayoutManager.EAST);
        rightPanel.oButton.setSelected(true);
        mainPanel.add(boardSizeLimiter);
        mainPanel.add(topPanel, borderLayoutManager.NORTH);
        mainPanel.add(bottomPanel, borderLayoutManager.SOUTH);

        
        add(mainPanel);
    }

    public void changeTurn() {
        if (turn == 0) {
            if(this.rightPanel.sButton.isSelected()) {
                bottomPanel.setTurn("Blue(S) Turn", Color.BLUE);
            } else {
                bottomPanel.setTurn("Blue(O) Turn", Color.BLUE);
            }
            turn = 1;
        } else {
            if(this.leftPanel.sButton.isSelected()) {
                bottomPanel.setTurn("Red(S) Turn", Color.RED);
            } else {
                bottomPanel.setTurn("Red(O) Turn", Color.RED);
            }
            turn = 0;
        }
    }

    public void displayWinner(String winner) {
        JOptionPane.showMessageDialog(this, winner + " is the winner", "Game End", JOptionPane.INFORMATION_MESSAGE);
    }
    public void displayDraw(){
         JOptionPane.showMessageDialog(this,"The game ended  in a draw", "Game End", JOptionPane.INFORMATION_MESSAGE);
    }
}
