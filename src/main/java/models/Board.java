package models;

import javax.swing.*;
//a game is represented by a board, which contains a 2-d array of cells
//TODO: determine if this should indeed extend jpanel. I don't think it should but IDK
public class Board {

    //cells state
    private Cell[][] cells;

    //constructors
    public Board(Cell[][] cells){
        this.cells = cells;
        initCells();

    }

    private void initCells(){
        for(int i = 0; i < this.getCells().length; i++){
            for(int j = 0; j < this.getCells()[i].length; j++){
                this.getCells()[i][j] = new Cell(false, false);
            }
        }
    }


    //cells setter
    public void setCells(Cell[][] cells){
        this.cells = cells;
    }

    //cells getter
    public Cell[][] getCells(){
        return this.cells;
    }

    //individual cell getter
    public Cell getCell(int row, int col){
        return this.getCells()[row][col];
    }

    //individual cell setter
    public void setCell(int row, int col, Cell cell){
        this.getCells()[row][col] = cell;
    }

}
