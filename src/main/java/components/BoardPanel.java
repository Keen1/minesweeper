package components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class BoardPanel extends JPanel{
    private static final Dimension CELL_DIM = new Dimension(25,25);


    private static final String MINE_PATH = "/images/mine.png";
    private static final String RED_FLAG_PATH = "/images/red_flag.png";

    private int boardRows;
    private int boardColumns;
    private int mineCount;

    private JButton[][] cellButtons;

    public BoardPanel(){
        this.boardRows = 9;
        this.boardColumns = 9;
        this.mineCount = 10;
        initComponents();
    }

    public BoardPanel(int boardRows, int boardColumns, int mineCount){
        this.boardRows = boardRows;
        this.boardColumns = boardColumns;
        this.mineCount = mineCount;
        initComponents();
    }




    private void initComponents(){

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < this.getCellButtons().length; i++){
            for(int j = 0; j < this.getCellButtons()[i].length; j++){
                gbc.gridx = i;
                gbc.gridy = j;
                add(this.getCellButtons()[i][j], gbc);
            }
        }

        setVisible(true);

    }








    private void initCellButtons(){
        int row = 9;
        int col = 9;
        this.cellButtons = new JButton[row][col];
        for(int i = 0; i < this.getBoardRows(); i++){
            for(int j = 0; j < this.getBoardColumns(); j++){
                this.cellButtons[i][j] = new JButton();
                this.cellButtons[i][j].setPreferredSize(CELL_DIM);
                this.cellButtons[i][j].setMinimumSize(CELL_DIM);

            }
        }
    }

    public JButton[][] getCellButtons(){

        if(this.cellButtons == null){
            initCellButtons();
        }

        return this.cellButtons;
    }

    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }
    public void setBoardRows(int boardRows){
        this.boardRows = boardRows;
    }
    public void setBoardColumns(int boardColumns){
        this.boardColumns = boardColumns;
    }

    public int getMineCount(){
        return this.mineCount;
    }

    public int getBoardRows(){
        return this.boardRows;
    }

    public int getBoardColumns(){
        return this.boardColumns;
    }



}
