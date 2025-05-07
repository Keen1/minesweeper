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

    public void updateFlaggedCount(){
        int flagged = this.getFlaggedCount();
        for(int i = 0; i < this.getBoard().getCells().length; i++){
            for(int j = 0; j < this.getBoard().getCells()[i].length; j++){
                if(this.getBoard().getCells()[i][j].hasUserFlag()){
                    flagged++;
                }
            }
        }
        this.setFlaggedCount(flagged);
    }

    public void updateUnflaggedBombCount(){
        int unflaggedBombs = this.getUnflaggedBombCount();
        for(int i = 0; i < this.getBoard().getCells().length; i++){
            for(int j = 0; j < this.getBoard().getCells()[i].length; j++){
                if(this.getBoard().getCells()[i][j].hasBomb() && !this.getBoard().getCells()[i][j].hasUserFlag()){
                    unflaggedBombs++;
                }
            }
        }
        this.setUnflaggedBombCount(unflaggedBombs);
    }

    public void resetBoard(){
        int row = this.getBoard().getCells().length;
        int col = this.getBoard().getCells()[row].length;
        Cell[][] cells = new Cell[row][col];
        Board board = new Board();
        board.setCells(cells);
        this.setBoard(board);
    }
    public void resetBoard(int row, int col){
        Cell[][] cells = new Cell[row][col];
        Board board = new Board();
        board.setCells(cells);
        this.setBoard(board);
    }

    public Board getBoard(){
        return this.board;
    }

    public void setBoard(Board board){
        this.board = board;
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
