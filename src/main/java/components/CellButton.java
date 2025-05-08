package components;

import javax.swing.*;

public class CellButton extends JButton {
    private final int row;
    private final int column;
    public CellButton(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
}
