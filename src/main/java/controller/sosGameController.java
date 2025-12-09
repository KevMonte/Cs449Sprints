package controller;

import javax.swing.*;

import model.*;


import view.*;

import java.awt.*;
import java.io.IOException;

public class sosGameController {
    private GUI gui;
    private String redPlayer;
    private String bluePlayer;

    String gameMode;

    private String gameState="";
   

    private sosGame gameModel;
    int oldRedScore;
    int oldBlueScore;

    int boardSize;

    private computerController redComputerController;
    private computerController blueComputerController;

    boolean redComputerSelected;
    boolean blueComputerSelected;
    
    private computerNode computerMove;

    private replayModel recordedGame;

    boolean replayingGame;

    
    public sosGameController(GUI gui) {
        this.gui = gui;
        this.gui.topPanel.newGameButton.addActionListener(e -> {
            try {
                newGame();
            } catch (IOException ex) {
            }
        });
        this.gui.bottomPanel.replayButton.addActionListener(e -> {
            try {
                replayGame();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException ex) {
            }
        });
        
        initBoardListeners(3);
        redPlayer = "S";
        bluePlayer = "O";
        gameModel = new sosSimpleGame(3,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        recordedGame = new replayModel(gameMode, boardSize, redComputerSelected, redPlayer, blueComputerSelected, bluePlayer);
        
    }

    private void newGame() throws IOException {
        if (this.gui.leftPanel.sButton.isSelected() == this.gui.rightPanel.sButton.isSelected()) {
            throw new IllegalArgumentException("Players cant both be S or O at same time");
        }
        boardSize = Integer.parseInt(this.gui.topPanel.boardSizeField.getText());
        if (boardSize < 3) {
            throw new IllegalArgumentException("Board must have a size of at least 3");
        }
        replayingGame = false;
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
            gameMode = "Simple";
            this.gameModel = new sosSimpleGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        } else {
            gameMode = "General";
            this.gameModel = new sosGeneralGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        }
        this.gui.leftPanel.setScore(0);
        this.gui.rightPanel.setScore(0);

        initBoardListeners(boardSize); 

        redComputerSelected = this.gui.leftPanel.computerButton.isSelected();
        blueComputerSelected = this.gui.rightPanel.computerButton.isSelected();
        if(gui.bottomPanel.recordCheckbox.isSelected()){
            recordedGame = new replayModel(gameMode, boardSize, redComputerSelected, redPlayer, blueComputerSelected, bluePlayer);
        }
        else if(recordedGame==null){
        recordedGame = new replayModel(gameMode, boardSize, redComputerSelected, redPlayer, blueComputerSelected, bluePlayer);
        }



        if(redComputerSelected) {
            
            redComputerController = new computerController(new computerModel(redPlayer,this.gui.boardPanel.boardButtons,boardSize));

            computerMove = redComputerController.computerMove();

            JButton cell = this.gui.boardPanel.boardButtons[computerMove.row][computerMove.col];
            makeMove(cell,computerMove.row,computerMove.col);

        }

        if(blueComputerSelected) {
            blueComputerController = new computerController(new computerModel(bluePlayer,this.gui.boardPanel.boardButtons,boardSize));
        }
        this.gui.boardSizeLimiter.add(this.gui.boardPanel);
        this.gui.revalidate();
        this.gui.repaint();
        //replayModel(String gameMode, int boardSize, boolean redPlayerComputer, String redPlayerLetter,boolean bluePLayerComputer, String bluePlayerLetter)

       
    }

    public void changeTurn() {
        if (gui.turn == 0) {
            if(gui.rightPanel.sButton.isSelected()) {
                gui.bottomPanel.setTurn("Blue(S) Turn", Color.BLUE);
            } else {
                gui.bottomPanel.setTurn("Blue(O) Turn", Color.BLUE);
            }
            gui.turn = 1;
        } else {
            if(gui.leftPanel.sButton.isSelected()) {
                gui.bottomPanel.setTurn("Red(S) Turn", Color.RED);
            } else {
                gui.bottomPanel.setTurn("Red(O) Turn", Color.RED);
            }
            gui.turn = 0;
        }
    }
    
    private void makeMove(JButton cell,int row,int col) throws IOException {
        if(!(gameState.equals(""))) {return;}

       
        if(!cell.getText().isEmpty()) {
            return;
        }
        if(gui.bottomPanel.recordCheckbox.isSelected() && !replayingGame){
            recordedGame.recordMove(row, col);
        }
        if(this.gui.turn == 0) {
            cell.setText(redPlayer);
            cell.setForeground(Color.RED);
           
           
            
            //cell.getSource()

        } else {
            cell.setText(bluePlayer);
            cell.setForeground(Color.BLUE);
        }
        this.gui.revalidate();
        this.gui.repaint();
        oldRedScore = gameModel.getRedScore();
        oldBlueScore = gameModel.getBlueScore();

        this.gameModel.checkSOS(row,col);



        this.gui.leftPanel.setScore(this.gameModel.getRedScore());
        this.gui.rightPanel.setScore(this.gameModel.getBlueScore());


        gameState = gameModel.getGameState();
        endCheck();


        //only changes turn if no points have been added
        if ((gameModel.getRedScore()==oldRedScore ) && (gameModel.getBlueScore()==oldBlueScore)) {
            
            changeTurn();


            if(this.gui.turn == 0 && redComputerSelected && !replayingGame) {
            
            redComputerController = new computerController(new computerModel(redPlayer,this.gui.boardPanel.boardButtons,boardSize));

            computerMove = redComputerController.computerMove();

            JButton redComputerCell = this.gui.boardPanel.boardButtons[computerMove.row][computerMove.col];
            makeMove(redComputerCell,computerMove.row,computerMove.col);

        }
        if(this.gui.turn == 1 && blueComputerSelected && !replayingGame) {
            
            blueComputerController = new computerController(new computerModel(bluePlayer,this.gui.boardPanel.boardButtons,boardSize));

            computerMove = blueComputerController.computerMove();

            JButton blueComputerCell = this.gui.boardPanel.boardButtons[computerMove.row][computerMove.col];
            makeMove(blueComputerCell,computerMove.row,computerMove.col);

        }
            return;
            
        }
        if(this.gui.turn == 0 && redComputerSelected&& !replayingGame) {
            
            redComputerController = new computerController(new computerModel(redPlayer,this.gui.boardPanel.boardButtons,boardSize));

            computerMove = redComputerController.computerMove();

            JButton redComputerCell = this.gui.boardPanel.boardButtons[computerMove.row][computerMove.col];
            makeMove(redComputerCell,computerMove.row,computerMove.col);

        }
         if(this.gui.turn == 1 && blueComputerSelected && !replayingGame) {
            
            blueComputerController = new computerController(new computerModel(bluePlayer,this.gui.boardPanel.boardButtons,boardSize));

            computerMove = blueComputerController.computerMove();

            JButton blueComputerCell = this.gui.boardPanel.boardButtons[computerMove.row][computerMove.col];
            makeMove(blueComputerCell,computerMove.row,computerMove.col);

        } 
        
        
        
        
        


        
    }
    private void replayGame() throws InterruptedException, IOException{
        
        if(recordedGame.fullGameRecorded==false||recordedGame==null){
            return;
            //throw new IllegalArgumentException("No game has Been Recorded");
        }
        
        replayingGame = true;//prevents it from recording stuff during replay mode
       // gui.bottomPanel.recordCheckbox.setSelected(false); //so it doesnt try record ing itself


        int recordedTurns= recordedGame.replayMoves.size();
       

        boardSize = recordedGame.boardSize;

        //these settings are important, since they matter to how the game was set up and the game setting might have changed since recording
        redPlayer = recordedGame.redPlayerLetter;
        redComputerSelected = recordedGame.redPlayerComputer;
        
        bluePlayer = recordedGame.bluePlayerLetter;
        blueComputerSelected = recordedGame.bluePLayerComputer;
       

        gui.topPanel.boardSizeField.setText(Integer.toString(boardSize));

        if(redPlayer.equals("S")){
            gui.leftPanel.sButton.setSelected(true);
            gui.rightPanel.oButton.setSelected(true);
        }
        else{
            gui.leftPanel.oButton.setSelected(true);
            gui.rightPanel.sButton.setSelected(true);
        }
        if(recordedGame.gameMode.equals("Simple")){
            gui.topPanel.simpleButton.setSelected(true);
        }
        else{
            gui.topPanel.generalButton.setSelected(true);
        }


        //these buttons kinda dont matter, but I recorded it anyways

        if(blueComputerSelected){
            gui.leftPanel.humanButton.setSelected(true);
        }
        else{
            gui.leftPanel.computerButton.setSelected(true);
        }
        if(redComputerSelected){
            gui.rightPanel.humanButton.setSelected(true);
        }
        else{
            gui.rightPanel.computerButton.setSelected(true);
        }



       
        this.gui.boardPanel.removeAll(); 
        this.gui.boardSizeLimiter.removeAll();

        this.gui.boardPanel = new BoardPanel(boardSize);
        this.gui.boardPanel.setPreferredSize(new Dimension(500,500));
        this.gui.boardSizeLimiter.add(this.gui.boardPanel);
        this.gui.revalidate();
        this.gui.repaint();
        


        if(recordedGame.gameMode.equals("Simple")){
            gameModel = new sosSimpleGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        }
        else{
            gameModel = new sosGeneralGame(boardSize,this.gui.boardPanel.boardButtons, redPlayer, bluePlayer, gui);
        }

        
        gameState = "";
        this.gui.leftPanel.setScore(0);
        this.gui.rightPanel.setScore(0);

        gui.turn=0;

        initBoardListeners(boardSize);

        for (int i=0; i<recordedTurns;i++){
            replayNode move = recordedGame.replayMoves.get(i);
            JButton cell = gui.boardPanel.boardButtons[move.row][move.col];
            makeMove(cell,move.row,move.col);
            
            this.gui.revalidate();
            this.gui.repaint();
            Thread.sleep(300);//just so you can see the moves being put on the board
            //endCheck();
            
        }



    }

    private void initBoardListeners(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final int r = row;
                final int c = col;
                this.gui.boardPanel.boardButtons[row][col].addActionListener(e -> {
                    try {
                        makeMove(this.gui.boardPanel.boardButtons[r][c],r,c);
                    } catch (IOException ex) {
                    }
                });
            }
        }
    }
    private void endCheck() throws IOException {
        
        if (gameState.equals("r")) {
            gui.displayWinner("Red");
            recordedGame.fullGameRecorded=true;
           // recordedGame.saveGametoFile();
        } 
        else if (gameState.equals("b")) {
            gui.displayWinner("Blue");
            recordedGame.fullGameRecorded=true;
           // recordedGame.saveGametoFile();
        } 
        else if (gameState.equals("d")) {
            gui.displayDraw();
            recordedGame.fullGameRecorded=true;
            //recordedGame.saveGametoFile();
        }
        
    }
}
