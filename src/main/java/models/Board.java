package models;

//a game is represented by a board, which contains a 2-d array of cells
public  class Board {
    private final Cell[][] cells;
    //constructor
    public Board(Cell[][] cells) {
        this.cells = cells;
        initCells();

    }

    //init the cells
    private void initCells() {
        for (int i = 0; i < this.getCells().length; i++) {
            for (int j = 0; j < this.getCells()[i].length; j++) {
                this.getCells()[i][j] = new Cell(false, false);
            }
        }
    }

    //individual cell getter
    public Cell getCell(int row, int col) {
        return this.getCells()[row][col];
    }

    public Cell[][] getCells(){
        return this.cells;
    }
}
