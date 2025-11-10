package view;


import javax.swing.*;

import java.awt.*;
import java.util.*;


import model.LineCoordinates;

public class BoardPanel extends JPanel {
    private final java.util.List<LineCoordinates> lineCoords = new ArrayList<>();
    public JButton[][] boardButtons;
    public Graphics graphics;
    //public Graphics2D graphics;

    public BoardPanel(int boardSize) {
        setLayout(new GridLayout(boardSize, boardSize));
        boardButtons = new JButton[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                JButton cell = new JButton("");
                boardButtons[row][col] = cell;
                add(cell);

            }
        }
         
      

        
    }
    public void addSOSLine(int r1, int c1, int r2, int c2){
        //lineCoords.add(new LineCoordinates(r1,c1,r2,c2));

    


    }
    /*protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (g);
        

    }*/
    
}

