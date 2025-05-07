package models;

//game model class
//contains the board and some summary stats regarding the game state
public class GameModel {

    //board and state attributes
    private Board board;
    private int flaggedCount;
    private int bombCount;
    private int unflaggedBombCount;

    //constructors
    public GameModel(){}
    public GameModel(Board board){
        this.board = board;
    }




    //update the count for the current number of flagged cells
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

    //update the count for the current number of bombs not flagged by the user
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

    //get the bomb status of a particular cell
    public boolean hasBomb(int row, int col){
        return this.getBoard().getCell(row, col).hasBomb();
    }

    //get the flagged status of a particular cell
    public boolean hasUserFlag(int row, int col){
        return this.getBoard().getCell(row, col).hasUserFlag();
    }

    //set the bomb status of a particular cell
    public void setBomb(int row, int col, boolean bomb){
        this.getBoard().getCell(row, col).setBomb(bomb);
    }

    //set the flagged status of a particular cell
    public void setUserFlag(int row, int col, boolean userFlag){
        this.getBoard().getCell(row, col).setUserFlag(userFlag);
    }

    //reset the board with the current row and column conditions
    public void resetBoard(){

        int row = this.getBoard().getCells().length;
        int col = this.getBoard().getCells()[row].length;

        Cell[][] cells = new Cell[row][col];
        Board board = new Board(cells);
        this.setBoard(board);
    }

    //reset the board with new row and column conditions
    public void resetBoard(int row, int col){
        Cell[][] cells = new Cell[row][col];
        Board board = new Board(cells);
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
