package model;

import view.*;
import javax.swing.*;

//import java.awt.*;
public class sosGame {

    int boardSize;
    int redScore;
    int blueScore;
    int turnNumber;
    private GUI gui;

    String redPlayer;
    String bluePlayer;
    JButton[][] boardButtons;


    public sosGame(int boardSize, JButton[][] boardButtons, String redPlayer, String bluePlayer,GUI gui) {
        this.boardSize = boardSize;

        this.redScore = 0;
        this.blueScore = 0;
        this.turnNumber = 0;
        this.boardButtons = boardButtons;
        this.redPlayer = redPlayer;
        this.bluePlayer = bluePlayer;
        this.gui = gui;
    }
    //move will have already been validated by controller
    public void checkSOS(int row, int col) {
        if (boardButtons[row][col].getText().equals("S")) {
            sCheck(row, col);
        } else {
            oCheck(row, col);
        }
        turnNumber++;
        //winCheck();




    }
    private void sCheck(int row, int col) {
        if (sValidUp(row)) {


            //up middle check
            if (boardButtons[row - 1][col].getText().equals("O") && boardButtons[row - 2][col].getText().equals("S")) {
                sScoreUp();
            }
            if (sValidLeft(col)) {
                //top left check
                if (boardButtons[row - 1][col - 1].getText().equals("O") && boardButtons[row - 2][col - 2].getText().equals("S")) {
                    sScoreUp();

                }
            }
            if (sValidRight(col, boardSize)) {
                //top right check
                if (boardButtons[row - 1][col + 1].getText().equals("O") && boardButtons[row - 2][col + 2].getText().equals("S")) {
                    sScoreUp();
                }
            }
        }



        if (sValidLeft(col)) {
            //middle left check
            if (boardButtons[row][col - 1].getText().equals("O") &&
                boardButtons[row][col - 2].getText().equals("S")) {
                sScoreUp();
            }
        }



        if (sValidRight(col, boardSize)) {
            //middle right check
            if (boardButtons[row][col + 1].getText().equals("O") &&
                boardButtons[row][col + 2].getText().equals("S")) {
                sScoreUp();
            }
        }
        if (sValidDown(row,boardSize)) {
            //down middle check
            if (boardButtons[row + 1][col].getText().equals("O") &&
                boardButtons[row + 2][col].getText().equals("S")) {
                sScoreUp();
            }

            //bottom left check
            if (sValidLeft(col)) {
                if (boardButtons[row + 1][col - 1].getText().equals("O") &&
                    boardButtons[row + 2][col - 2].getText().equals("S")) {
                    sScoreUp();
                }
            }
            
            if (sValidRight(col, boardSize)) {
                //bottom right check
                if (boardButtons[row + 1][col + 1].getText().equals("O") &&
                    boardButtons[row + 2][col + 2].getText().equals("S")) {
                    sScoreUp();
                }
            }
        }





    }
    private void oCheck(int row, int col) {
        if (oValidVertical(row, boardSize)) {
            
            if (boardButtons[row - 1][col].getText().equals("S") &&
                boardButtons[row + 1][col].getText().equals("S")) {
                oScoreUp();
            }
        }
        if (oValidHorizontal(col, boardSize)) {
            
            if (boardButtons[row][col - 1].getText().equals("S") &&
                boardButtons[row][col + 1].getText().equals("S")) {
                oScoreUp();
            }
        }
        if (oValidSlash(row, col, boardSize)) {
            
            if (boardButtons[row - 1][col - 1].getText().equals("S") &&
                boardButtons[row + 1][col + 1].getText().equals("S")) {
                oScoreUp();
            }
        }
        if (oValidBackSlash(row, col, boardSize)) {
           
            if (boardButtons[row - 1][col + 1].getText().equals("S") &&
                boardButtons[row + 1][col - 1].getText().equals("S")) {
                oScoreUp();
            }
        }
        
    }
    public String getGameState(){return "";}//subclass implementation, diff for simple & general

    private void sScoreUp() {
        if (redPlayer.equals("S")) {
            redScore++;
        } else {
            blueScore++;
        }
    }
    private void oScoreUp() {
        if (redPlayer.equals("O")) {
            redScore++;
        } else {
            blueScore++;
        }
    }


    public int getRedScore() {
        return redScore;
    }
    public int getBlueScore() {
        return blueScore;
    }


    private boolean sValidDown(int row, int boardSize) {
        return row + 2 < boardSize;

    }
    private boolean sValidUp(int row) {
        return row - 2 >= 0;
    }
    private boolean sValidLeft(int col) {
        return col - 2 >= 0;
    }
    private boolean sValidRight(int col, int boardSize) {
        return col + 2 < boardSize;
    }


    private boolean oValidVertical(int row, int boardSize) {
        return row - 1 >= 0 && row + 1 < boardSize;
    }
    private boolean oValidHorizontal(int col, int boardSize) {
        return col - 1 >= 0 && col + 1 < boardSize;
    }
    private boolean oValidSlash(int row, int col, int boardSize) {
        return row - 1 >= 0 && col - 1 >= 0 && row + 1 < boardSize && col + 1 < boardSize;
    }
    private boolean oValidBackSlash(int row, int col, int boardSize) {
        return row - 1 >= 0 && col + 1 < boardSize && row + 1 < boardSize && col - 1 >= 0;
    }




}