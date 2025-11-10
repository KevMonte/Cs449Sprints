package controller;

import javax.swing.*;
import model.*;


import view.*;

import java.awt.*;

public class sosGameController {
    private GUI gui;
    private String redPlayer;
    private String bluePlayer;

    private String gameState="";

    private sosGame gameModel;
    int oldRedScore;
    int oldBlueScore;
    
    public sosGameController(GUI gui) {
        this.gui = gui;
        this.gui.topPanel.newGameButton.addActionListener(e -> newGame());
        initBoardListeners(3);
        redPlayer = "S";
        bluePlayer = "O";
        gameModel = new sosSimpleGame(3,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        
    }

    private void newGame() {
        if (this.gui.leftPanel.sButton.isSelected() == this.gui.rightPanel.sButton.isSelected()) {
            throw new IllegalArgumentException("Players cant both be S or O at same time");
        }
        int boardSize = Integer.parseInt(this.gui.topPanel.boardSizeField.getText());
        if (boardSize < 3) {
            throw new IllegalArgumentException("Board must have a size of at least 3");
        }
        this.gui.turn = 0;
        gameState = "";

        this.gui.boardPanel.removeAll(); 
        this.gui.boardSizeLimiter.removeAll(); 

        this.gui.boardPanel = new BoardPanel(boardSize);
        this.gui.boardPanel.setPreferredSize(new Dimension(500,500));
        if(this.gui.leftPanel.sButton.isSelected()) {
            redPlayer = "S"; 
            bluePlayer = "O";
        } else {
            redPlayer = "O"; 
            bluePlayer = "S";
        }
        //this.gameModel = new sosGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer);
        if(this.gui.topPanel.simpleButton.isSelected()) {
            this.gameModel = new sosSimpleGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        } else {
            this.gameModel = new sosGeneralGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        }
        this.gui.leftPanel.setScore(0);
        this.gui.rightPanel.setScore(0);

        initBoardListeners(boardSize);
        
        this.gui.boardSizeLimiter.add(this.gui.boardPanel);
        this.gui.revalidate();
        this.gui.repaint();
    }
    
    private void makeMove(JButton cell,int row,int col) {
        if(!(gameState.equals(""))) {return;}

       
        if(!cell.getText().isEmpty()) {
            return;
        }
        if(this.gui.turn == 0) {
            cell.setText(redPlayer);
            cell.setForeground(Color.RED);
           
            
            //cell.getSource()

        } else {
            cell.setText(bluePlayer);
            cell.setForeground(Color.BLUE);
        }
        oldRedScore = gameModel.getRedScore();
        oldBlueScore = gameModel.getBlueScore();

        this.gameModel.checkSOS(row,col);



        this.gui.leftPanel.setScore(this.gameModel.getRedScore());
        this.gui.rightPanel.setScore(this.gameModel.getBlueScore());


        gameState = gameModel.getGameState();
        endCheck();


        //only changes turn if no points have been added
        if ((gameModel.getRedScore()==oldRedScore ) && (gameModel.getBlueScore()==oldBlueScore)) {
            
            this.gui.changeTurn();
            return;
        } 
        
        
        
        


        
    }

    private void initBoardListeners(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final int r = row;
                final int c = col;
                this.gui.boardPanel.boardButtons[row][col].addActionListener(e -> makeMove(this.gui.boardPanel.boardButtons[r][c],r,c));
            }
        }
    }
    private void endCheck() {
        if (gameState.equals("r")) {
            gui.displayWinner("Red");
        } 
        else if (gameState.equals("b")) {
            gui.displayWinner("Blue");
        } 
        else if (gameState.equals("d")) {
            gui.displayDraw();
        }
    }
}
