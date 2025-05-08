package handlers.difficulty;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyHandler implements ActionListener {
    private final GameController gameController;
    private final Dimension dimension;
    private final int rowCount;
    private final int columnCount;
    private final int mineCount;

    public DifficultyHandler(GameController gameController, Dimension dimension, int rowCount, int columnCount, int mineCount){
        this.gameController = gameController;
        this.dimension = dimension;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
    }

    @Override
    public void actionPerformed(ActionEvent event){

        this.getGameController().setModelDifficulty(this.getRowCount(), this.getColumnCount(), this.getMineCount());
        this.getGameController().setFrameBoardPanel(this.getRowCount(), this.getColumnCount(), this.getMineCount(), this.getDimension());

    }

    public GameController getGameController(){
        return this.gameController;
    }
    public Dimension getDimension(){
        return this.dimension;
    }
    public int getRowCount(){
        return this.rowCount;
    }
    public int getColumnCount(){
        return this.columnCount;
    }
    public int getMineCount(){
        return this.mineCount;
    }

}
