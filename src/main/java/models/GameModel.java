package models;

import java.security.SecureRandom;

//game model class
//contains the board and some summary stats regarding the game state
public class GameModel {

    //board and state attributes
    private Board board;
    private int flaggedCount;
    private int mineCount;
    private int unflaggedMineCount;
    private int minesFlaggedCount;

    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 9;
    private static final int DEFAULT_MINES= 10;

    //constructors
    public GameModel(){
        initDefaultBoard();
    }
    public GameModel(Board board){
        this.board = board;
    }

    //init the default board layout
    private void initDefaultBoard(){
        this.setMineCount(DEFAULT_MINES);
        Cell[][] cells = new Cell[DEFAULT_ROWS][DEFAULT_COLS];
        Board board = new Board(cells);
        this.setBoard(board);
        this.setMines();

    }

    public boolean isGameWon(){
        return this.getMinesFlaggedCount() == this.getMineCount();
    }

    public void updateMinesFlaggedCount(){
        int flagged = 0;
        for(int i = 0; i < this.getBoard().getCells().length; i++){
            for(int j = 0; j < this.getBoard().getCells()[i].length; j++){
                if(this.getBoard().getCell(i, j).hasUserFlag() && this.getBoard().getCell(i, j).hasMine()){
                    flagged++;
                }
            }
        }

        this.setMinesFlaggedCount(flagged);

    }






    //update the count for the current number of flagged cells
    public void updateFlaggedCount(){

        int flagged = 0;
        for(int i = 0; i < this.getBoard().getCells().length; i++){
            for(int j = 0; j < this.getBoard().getCells()[i].length; j++){
                if(this.getBoard().getCells()[i][j].hasUserFlag()){
                    flagged++;
                }
            }
        }
        this.setFlaggedCount(flagged);
    }

    //update the count for the current number of mines not flagged by the user
    public void updateUnflaggedMineCount(){
        int unflaggedMines = 0;
        for(int i = 0; i < this.getBoard().getCells().length; i++){
            for(int j = 0; j < this.getBoard().getCells()[i].length; j++){
                if(this.getBoard().getCells()[i][j].hasMine() && !this.getBoard().getCells()[i][j].hasUserFlag()){
                    unflaggedMines++;
                }
            }
        }
        this.setUnflaggedMineCount(unflaggedMines);
    }

    //determine which cells adjacent to the specified cell have a mines
    public int getAdjacentMinesCount(int row, int col){

        int count = 0;

        for(int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for(int colOffset = -1; colOffset <= 1; colOffset++){
                if(rowOffset == 0 && colOffset == 0){
                    continue;
                }

                int nextRow = rowOffset + row;
                int nextCol = colOffset + col;
                if(nextRow >= 0 && nextRow < this.getBoard().getCells().length &&
                        nextCol >=0 && nextCol < this.getBoard().getCells()[nextRow].length){

                    if(this.getBoard().getCells()[nextRow][nextCol].hasMine()){
                        count++;
                    }

                }
            }
        }
        return count;


    }

    //set a given number of mines on the board given a count
    public void setMines(){

        if(this.getMineCount() != 0){

            int count = this.getMineCount();
            SecureRandom secureRand = new SecureRandom();
            int maxRows = this.getBoard().getCells().length;
            int maxCols = this.getBoard().getCells()[maxRows -1].length;

            while(count > 0){
                int randRow = secureRand.nextInt(maxRows);
                int randCol = secureRand.nextInt(maxCols);
                if(!this.getBoard().getCell(randRow, randCol).hasMine()){
                    this.getBoard().getCell(randRow, randCol).setMine(true);
                    count--;
                }

            }

        }

    }

    //get the numbers of bombs that the user has yet to flag
    public int getUnflaggedMinesRemaining(){

        int unflaggedMines = 0;

        for(int i = 0; i < this.getBoard().getCells().length; i++){
            for(int j = 0; j < this.getBoard().getCells()[i].length; j++){
                if(this.getBoard().getCell(i, j).hasMine() && !this.getBoard().getCell(i, j).hasUserFlag()){
                    unflaggedMines++;
                }
            }
        }

        return unflaggedMines;
    }

    //reset the board with the current row and column conditions
    public void resetBoard(){

        int row = this.getBoard().getCells().length;
        int col = this.getBoard().getCells()[row - 1].length;

        Cell[][] cells = new Cell[row][col];
        Board board = new Board(cells);
        this.setBoard(board);
        this.setMines();
    }

    //reset the board with new row and column conditions
    public void resetBoard(int row, int col){
        Cell[][] cells = new Cell[row][col];
        Board board = new Board(cells);
        this.setBoard(board);
        this.setMines();
    }

    public void resetBoard(int row, int col, int mineCount){
        Cell[][] cells = new Cell[row][col];
        Board board = new Board(cells);
        this.setBoard(board);
        this.setMineCount(mineCount);
        this.setMines();
    }

    //get the mines status of a particular cell
    public boolean hasMine(int row, int col){
        return this.getBoard().getCell(row, col).hasMine();
    }

    //get the flagged status of a particular cell
    public boolean hasUserFlag(int row, int col){
        return this.getBoard().getCell(row, col).hasUserFlag();
    }

    //set the mine status of a particular cell
    public void setMine(int row, int col, boolean bomb){
        this.getBoard().getCell(row, col).setMine(bomb);
    }

    //set the flagged status of a particular cell
    public void setUserFlag(int row, int col, boolean userFlag){
        this.getBoard().getCell(row, col).setUserFlag(userFlag);
    }

    //get the game board
    public Board getBoard(){
        return this.board;
    }

    //set the game board
    public void setBoard(Board board){
        this.board = board;
    }

    public void setMinesFlaggedCount(int minesFlaggedCount){
        this.minesFlaggedCount = minesFlaggedCount;
    }

    public int getMinesFlaggedCount(){
        return this.minesFlaggedCount;
    }

    //set the flagged count
    public void setFlaggedCount(int flaggedCount){
        this.flaggedCount = flaggedCount;
    }

    //get the flagged count
    public int getFlaggedCount(){
        return this.flaggedCount;
    }

    //set the mine count
    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    //get the mine count
    public int getMineCount(){
        return this.mineCount;
    }

    //set number of unflagged mines
    public void setUnflaggedMineCount(int unflaggedBombCount){
        this.unflaggedMineCount = unflaggedBombCount;
    }

    //get the number of unflagged mines
    public int getUnflaggedMineCount(){
        return this.unflaggedMineCount;
    }




}
