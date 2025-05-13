package handlers.cells;

import components.CellButton;
import controllers.GameController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FloodFillHandler extends MouseAdapter {
    private final GameController gameController;
    public FloodFillHandler(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int column = source.getColumn();


    }

    public void loadImage(CellButton button, ImageIcon image){
        this.getGameController().setCellButtonImageIcon(button, image);

    }

    public int getAdjacentMineCount(int row, int col){
        return this.getGameController().getAdjacentMines(row, col);
    }

    public boolean hasMine(int row, int col){
        return this.getGameController().hasMine(row, col);
    }



    public GameController getGameController(){
        return this.gameController;
    }
}
