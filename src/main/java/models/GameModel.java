package models;

public class GameModel {
    private Board board;
    private int flaggedCount;
    private int bombCount;
    private int unflaggedBombCount;

    public GameModel(){}
    public GameModel(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return this.board;
    }

    public boolean hasBomb(int row, int col){
        return this.getBoard().getCell(row, col).hasBomb();
    }
    public boolean hasUserFlag(int row, int col){
        return this.getBoard().getCell(row, col).hasUserFlag();
    }
    public void setBomb(int row, int col, boolean bomb){
        this.getBoard().getCell(row, col).setBomb(bomb);
    }
    public void setUserFlag(int row, int col, boolean userFlag){
        this.getBoard().getCell(row, col).setUserFlag(userFlag);
    }



    public void setFlaggedCount(int flaggedCount){
        this.flaggedCount = flaggedCount;
    }
    public int getFlaggedCount(){
        return this.flaggedCount;
    }
    public void setBombCount(int bombCount){
        this.bombCount = bombCount;
    }
    public int getBombCount(){
        return this.bombCount;
    }
    public void setUnflaggedBombCount(int unflaggedBombCount){
        this.unflaggedBombCount = unflaggedBombCount;
    }
    public int getUnflaggedBombCount(){
        return this.unflaggedBombCount;
    }



}
