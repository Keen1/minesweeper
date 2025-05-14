package models;

/**
 * @param cells cells state
 */ //a game is represented by a board, which contains a 2-d array of cells
public record Board(Cell[][] cells) {

    //constructor
    public Board(Cell[][] cells) {
        this.cells = cells;
        initCells();

    }

    //init the cells
    private void initCells() {
        for (int i = 0; i < this.cells().length; i++) {
            for (int j = 0; j < this.cells()[i].length; j++) {
                this.cells()[i][j] = new Cell(false, false);
            }
        }
    }

    //individual cell getter
    public Cell getCell(int row, int col) {
        return this.cells()[row][col];
    }

}
