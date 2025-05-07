package models;

import javax.swing.*;

public class Board extends JPanel {
    private Cell[][] cells;
    public Board(){
    }
    public Board(Cell[][] cells){
        this.cells = cells;
    }

    public void setCells(Cell[][] cells){
        this.cells = cells;
    }
    public Cell[][] getCells(){
        return this.cells;
    }

    public Cell getCell(int row, int col){
        return this.getCells()[row][col];
    }
    public void setCell(int row, int col, Cell cell){
        this.getCells()[row][col] = cell;
    }

}
