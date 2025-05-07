package components;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel{

    private JButton[][] cellButtons;

    public BoardPanel(){
        initComponents();
    }
    public BoardPanel(JButton[][] cellButtons){
        this.cellButtons = cellButtons;
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
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                this.cellButtons[i][j] = new JButton("x");
            }
        }
    }

    public JButton[][] getCellButtons(){

        if(this.cellButtons == null){
            initCellButtons();
        }

        return this.cellButtons;
    }



}
