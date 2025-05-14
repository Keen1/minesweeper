package components;


import models.GameModel;
import utils.ImageIconPathVars;
import utils.NumberIconPathVars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.util.Objects;

/*
* the board panel class
* holds the "board" of the game, all the cells that may or may not contain mines
* the gui realizes the cells as buttons with listeners attached in the controller
*/
public class BoardPanel extends JPanel{
    //static dimension for cells
    private static final Dimension CELL_DIM = new Dimension(25,25);

    //variables for board attributes
    private int boardRows;
    private int boardColumns;
    private int mineCount;

    //component variables
    private CellButton[][] cellButtons;
    private JPanel buttonPanel;
    private JPanel glassPanel;

    private JLayeredPane layeredPane;

    //default constructor
    public BoardPanel(){
        this.boardRows = 9;
        this.boardColumns = 9;
        this.mineCount = 10;
        initComponents();
    }

    //overloaded constructor
    public BoardPanel(int boardRows, int boardColumns, int mineCount){
        this.boardRows = boardRows;
        this.boardColumns = boardColumns;
        this.mineCount = mineCount;
        initComponents();
    }

    //initialize the components of the board panel
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

        this.getLayeredPane().add(this.getGlassPanel(), JLayeredPane.PALETTE_LAYER);
        this.getLayeredPane().add(this.getButtonPanel(), JLayeredPane.DEFAULT_LAYER);

        add(this.getLayeredPane(), BorderLayout.CENTER);

        setVisible(true);


    }

    //disable the button panel via enabling the glass panel
    public void disableButtonPanel(){
        this.getGlassPanel().setVisible(true);
    }

    //enable the button panel via disabling the glass panel
    public void enableButtonPanel(){
        this.getGlassPanel().setVisible(false);
    }

    //change the size of the board
    public void changeBoardSize(int rows, int cols, int mineCount){
        this.setBoardRows(rows);
        this.setBoardColumns(cols);
        this.setMineCount(mineCount);
        this.initCellButtons();
    }

    //initialize the layered pane
    private void initLayeredPane(){
        this.layeredPane = new JLayeredPane();
        this.layeredPane.setLayout(new OverlayLayout(this.layeredPane));
    }

    //get the layered pane
    public JLayeredPane getLayeredPane(){
        if(this.layeredPane == null){
            initLayeredPane();
        }
        return this.layeredPane;
    }

    //initialize the glass panel
    private void initGlassPanel(){
        this.glassPanel = new JPanel();
        this.glassPanel.setOpaque(false);
        this.glassPanel.setVisible(false);

        this.glassPanel.addMouseListener(new MouseAdapter(){});
        this.glassPanel.addMouseMotionListener(new MouseMotionAdapter(){});
        this.glassPanel.addKeyListener(new KeyAdapter(){});
        this.glassPanel.setFocusTraversalKeysEnabled(false);

    }

    //get the glass panel
    public JPanel getGlassPanel(){

        if(this.glassPanel == null){
            initGlassPanel();
        }

        return this.glassPanel;
    }

    //initialize the button panel
    private void initButtonPanel(){
        this.buttonPanel = new JPanel(new GridBagLayout());
    }

    //get the button panel
    public JPanel getButtonPanel(){
        if(this.buttonPanel == null){
            initButtonPanel();
        }
        return this.buttonPanel;
    }

    //initialize the cell buttons
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

    public void setImagePaths(GameModel model){
        for(int i = 0; i < this.getCellButtons().length; i++){
            for(int j = 0; j < this.getCellButtons()[i].length; j++){

                if(model.hasMine(i, j)){
                    this.getCellButton(i, j).setImagePathString(ImageIconPathVars.getPathFromCode("mine"));

                }else if(model.getAdjacentMinesCount(i, j) > 0){
                    int mineCount = model.getAdjacentMinesCount(i, j);
                    String imagePath = NumberIconPathVars.getPathFromInt(mineCount);
                    this.getCellButton(i, j).setImagePathString(imagePath);

                }
            }
        }
    }

    //get the cell buttons
    public CellButton[][] getCellButtons(){

        if(this.cellButtons == null){
            initCellButtons();
        }

        return this.cellButtons;
    }

    public CellButton getCellButton(int row, int column){
        return this.getCellButtons()[row][column];
    }

    //set the cell button's image icon
    public void setCellImageIcon(CellButton button, ImageIcon icon){
        button.setIcon(icon);
    }

    public void setFlagIcon(int row, int col){
        URL url = getClass().getResource(ImageIconPathVars.getPathFromCode("flag"));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(url));
        this.getCellButton(row, col).setIcon(icon);
    }

    public void setButtonIconToNull(int row, int col){
        CellButton button = this.getCellButton(row, col);
        button.setIcon(null);
        button.revalidate();
        button.repaint();

    }

    //set the mine count
    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    //set the board rows
    public void setBoardRows(int boardRows){
        this.boardRows = boardRows;
    }

    //set the board columns
    public void setBoardColumns(int boardColumns){
        this.boardColumns = boardColumns;
    }

    //get the mine count
    public int getMineCount(){
        return this.mineCount;
    }

    //get the board rows
    public int getBoardRows(){
        return this.boardRows;
    }

    //get the board columns
    public int getBoardColumns(){
        return this.boardColumns;
    }



}
