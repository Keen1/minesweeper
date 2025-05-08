package components;

import handlers.cells.CellMineImageHandler;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel{
    private static final Dimension CELL_DIM = new Dimension(25,25);


    private static final String MINE_PATH = "/images/mine.png";
    private static final String RED_FLAG_PATH = "/images/red_flag.png";

    private int boardRows;
    private int boardColumns;
    private int mineCount;

    private CellButton[][] cellButtons;
    private JPanel buttonPanel;

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
        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < this.getCellButtons().length; i++){
            for(int j = 0; j < this.getCellButtons()[i].length; j++){
                gbc.gridx = i;
                gbc.gridy = j;
                this.getButtonPanel().add(this.getCellButtons()[i][j], gbc);
            }
        }

        add(this.getButtonPanel(), BorderLayout.CENTER);
        setVisible(true);


    }



    public void changeBoardSize(int rows, int cols, int mineCount){
        this.setBoardRows(rows);
        this.setBoardColumns(cols);
        this.setMineCount(mineCount);
        this.initCellButtons();
    }


    private void initButtonPanel(){
        this.buttonPanel = new JPanel(new GridBagLayout());
    }
    public JPanel getButtonPanel(){
        if(this.buttonPanel == null){
            initButtonPanel();
        }
        return this.buttonPanel;
    }


    private void initCellButtons(){
        this.cellButtons = new CellButton[this.getBoardRows()][this.getBoardColumns()];
        for(int i = 0; i < this.getBoardRows(); i++){
            for(int j = 0; j < this.getBoardColumns(); j++){
                this.cellButtons[i][j] = new CellButton(i, j);
                this.cellButtons[i][j].setPreferredSize(CELL_DIM);
                this.cellButtons[i][j].setMinimumSize(CELL_DIM);

            }
        }
    }

    public void setCellButtons(CellButton[][] cellButtons){
        this.cellButtons = cellButtons;
    }

    public CellButton[][] getCellButtons(){

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
