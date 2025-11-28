package model;

import javax.swing.JButton;

public class computerModel {
    public String soType;
    public JButton[][] boardButtons;
    public int boardSize;
    public computerModel(String soType, JButton[][] boardButtons, int boardSize) {
        this.soType = soType;
        this.boardButtons = boardButtons;
        this.boardSize = boardSize;
    }
    
    
}

