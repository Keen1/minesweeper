package components;

import javax.swing.*;

/*
*Cell button for front end. Tracks the location of the button via the row and column attribute while maintaining
* JButton behavior through extension
*/
public class CellButton extends JToggleButton {
    //attributes
    private final int row;
    private final int column;

    //constructor
    public CellButton(int row, int column){

        this.row = row;
        this.column = column;
    }

    //get the row of the button
    public int getRow(){
        return this.row;
    }

    //bet the column of the button
    public int getColumn(){
        return this.column;
    }
}
