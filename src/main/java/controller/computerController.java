package controller;

import model.computerModel;
import model.computerNode;

public class computerController {
    private computerModel computerModel;
    //private computerNode computerNode;
    int  boardSize;

    public computerController(computerModel computerModel) {
        this.computerModel = computerModel;
        this.boardSize = computerModel.boardSize;
    }
    public computerNode computerMove(){
        //checks if it can made any points
        for (int row = 0; row < computerModel.boardSize; row++) {
            for (int col = 0; col < computerModel.boardSize; col++) {
                if (computerModel.boardButtons[row][col].getText().equals("")) {
                    computerNode move = soMove(row, col);
                    if (move.pointMove) {
                        return move;
                    }

                }
            }
        }
        //if no point moves possible goes for an empty corner, in the order, top left, bottrom right top right, bottom left
        if (computerModel.boardButtons[0][0].getText().equals("")) {
            return new computerNode(0, 0, false);
        }

        if (computerModel.boardButtons[boardSize - 1][boardSize - 1].getText().equals("")) {
            return new computerNode(computerModel.boardSize - 1, computerModel.boardSize - 1, false);
        }
        if (computerModel.boardButtons[0][boardSize - 1].getText().equals("")) {
            return new computerNode(0, computerModel.boardSize - 1, false);
        }
        if (computerModel.boardButtons[boardSize - 1][0].getText().equals("")) {
            return new computerNode(computerModel.boardSize - 1, 0, false);
        }

        //if no point moves can be made and no corner available, will go for first available move
       for (int row = 0; row < computerModel.boardSize; row++) {
            for (int col = 0; col < computerModel.boardSize; col++) {
                if (computerModel.boardButtons[row][col].getText().equals("")) {
                    return new computerNode(row, col, false);
                }
            }
        }

        return new computerNode(0, 0, false);//something went wrong lmao

    }

    private computerNode soMove(int row, int col){
        if (computerModel.soType.equals("S")){
            return sCheck(row, col);

        } 
        else {
            return oCheck(row, col);

        }
    }





    private computerNode pointMove(int row, int col) {
        return new computerNode(row, col, true);
    }
     private computerNode noPointMove(int row, int col) {
        return new computerNode(row, col, false);
    }

    private computerNode sCheck(int row, int col) {
        if (sValidUp(row)) {


            //up middle check
            if (computerModel.boardButtons[row - 1][col].getText().equals("O") && computerModel.boardButtons[row - 2][col].getText().equals("S")) {
                return pointMove(row , col);
            }
            if (sValidLeft(col)) {
                //top left check
                if (computerModel.boardButtons[row - 1][col - 1].getText().equals("O") && computerModel.boardButtons[row - 2][col - 2].getText().equals("S")) {
                    return pointMove(row , col);

                }
            }
            if (sValidRight(col, boardSize)) {
                //top right check
                if (computerModel.boardButtons[row - 1][col + 1].getText().equals("O") && computerModel.boardButtons[row - 2][col + 2].getText().equals("S")) {
                    return pointMove(row , col);
                }
            }
        }



        if (sValidLeft(col)) {
            //middle left check
            if (computerModel.boardButtons[row][col - 1].getText().equals("O") &&
                computerModel.boardButtons[row][col - 2].getText().equals("S")) {
                return pointMove(row , col);
            }
        }



        if (sValidRight(col, computerModel.boardSize)) {
            //middle right check
            if (computerModel.boardButtons[row][col + 1].getText().equals("O") &&
                computerModel.boardButtons[row][col + 2].getText().equals("S")) {
                return pointMove(row , col);
            }
        }
        if (sValidDown(row,computerModel.boardSize)) {
            //down middle check
            if (computerModel.boardButtons[row + 1][col].getText().equals("O") &&
                computerModel.boardButtons[row + 2][col].getText().equals("S")) {
                return pointMove(row , col);
            }

            //bottom left check
            if (sValidLeft(col)) {
                if (computerModel.boardButtons[row + 1][col - 1].getText().equals("O") &&
                    computerModel.boardButtons[row + 2][col - 2].getText().equals("S")) {
                    return pointMove(row , col);
                }
            }
            
            if (sValidRight(col, boardSize)) {
                //bottom right check
                if (computerModel.boardButtons[row + 1][col + 1].getText().equals("O") &&
                    computerModel.boardButtons[row + 2][col + 2].getText().equals("S")) {
                    return pointMove(row , col);
                }
            }

        }
        return noPointMove(row,col);





    }
    private computerNode oCheck(int row, int col) {
        if (oValidVertical(row, boardSize)) {
            
            if (computerModel.boardButtons[row - 1][col].getText().equals("S") &&
                computerModel.boardButtons[row + 1][col].getText().equals("S")) {
                return pointMove(row , col);
            }
        }
        if (oValidHorizontal(col, boardSize)) {
            
            if (computerModel.boardButtons[row][col - 1].getText().equals("S") &&
                computerModel.boardButtons[row][col + 1].getText().equals("S")) {
                return pointMove(row , col);
            }
        }
        if (oValidSlash(row, col, computerModel.boardSize)) {
            
            if (computerModel.boardButtons[row - 1][col - 1].getText().equals("S") &&
                computerModel.boardButtons[row + 1][col + 1].getText().equals("S")) {
                return pointMove(row , col);
            }
        }
        if (oValidBackSlash(row, col, computerModel.boardSize)) {
           
            if (computerModel.boardButtons[row - 1][col + 1].getText().equals("S") &&
                computerModel.boardButtons[row + 1][col - 1].getText().equals("S")) {
                return pointMove(row , col);
            }
        }
        return noPointMove(row,col);
        
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

    
    
    

