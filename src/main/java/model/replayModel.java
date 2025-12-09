package model;


import java.io.*;

import java.util.ArrayList;

import model.replayNode;

public class replayModel {
    
    public String gameMode;
    public ArrayList <replayNode> replayMoves;
    public int boardSize;

    public boolean redPlayerComputer;
    public String redPlayerLetter;

    public boolean bluePLayerComputer;
    public String bluePlayerLetter;

    public boolean fullGameRecorded=false;

    String fileName="recordedGame.txt";

    File file;


    public replayModel(String gameMode, int boardSize, boolean redPlayerComputer, String redPlayerLetter,boolean bluePLayerComputer, String bluePlayerLetter) {
        
        this.gameMode = gameMode;
        this.boardSize = boardSize;


        this.redPlayerComputer = redPlayerComputer;
        this.redPlayerLetter = redPlayerLetter;

        this.bluePLayerComputer = bluePLayerComputer;
        this.bluePlayerLetter = bluePlayerLetter;
        this.replayMoves = new ArrayList<replayNode>();
    }

    public void recordMove(int row, int col) {
        replayNode move = new replayNode(row, col);
        replayMoves.add(move);
    }

    public void saveGametoFile() throws IOException {
        PrintWriter writer= new PrintWriter(new FileWriter(fileName,false));
        writer.println(gameMode+","+boardSize+","+redPlayerComputer+","+redPlayerLetter+","+bluePLayerComputer+","+bluePlayerLetter);
        writer.close();
        file = new File(fileName);


        for (int i=0;i<replayMoves.size();i++) {
            replayNode move = replayMoves.get(i);
            
        }
    }

    /*  public void recordMove(int row, int col, String letter) {
        replayNode move = new replayNode(row, col, letter);
        replayMoves.add(move);
    }  */

    
}
