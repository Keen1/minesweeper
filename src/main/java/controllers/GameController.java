package controllers;

import components.BoardPanel;
import components.GameFrame;
import components.ScoreBoardPanel;
import handlers.cells.CellMineImageHandler;
import handlers.cells.CellPressAndReleaseHandler;
import models.Cell;
import models.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameController {

    private GameFrame gameFrame;
    private BoardPanel boardPanel;
    private ScoreBoardPanel scoreBoardPanel;
    private GameModel gameModel;
    public GameController(){

    }

    public GameController(GameFrame gameFrame, BoardPanel boardPanel, ScoreBoardPanel scoreBoardPanel){
        this.gameFrame = gameFrame;
        this.boardPanel = boardPanel;
        this.scoreBoardPanel = scoreBoardPanel;

    }
    public GameController(GameFrame gameFrame, BoardPanel boardPanel, ScoreBoardPanel scoreBoardPanel, GameModel gameModel){
        this.gameFrame = gameFrame;
        this.boardPanel = boardPanel;
        this.scoreBoardPanel = scoreBoardPanel;
        this.gameModel = gameModel;
        registerCellMouseHandlers();
    }


    public void setModelDifficulty(int row, int col, int mineCount){
        this.getGameModel().resetBoard(row, col, mineCount);
        this.getBoardPanel().changeBoardSize(row, col, mineCount);
    }

    public void setFrameBoardPanel(int row, int col, int mineCount, Dimension dimension){
        this.getGameFrame().changeBoardPanel(row, col, mineCount, dimension);
    }





    public void registerCellMouseHandlers(){
        JButton statusButton = this.getScoreBoardPanel().getGameStatusButton();
        JButton[][] cellButtons = this.getBoardPanel().getCellButtons();
        for (JButton[] cellRow : cellButtons) {
            for (JButton cell : cellRow) {
                cell.addMouseListener(new CellPressAndReleaseHandler(statusButton));
                cell.addMouseListener(new CellMineImageHandler(this));
            }
        }
    }

    public int getAdjacentMines(int row, int col){
        return this.getGameModel().getAdjacentMinesCount(row, col);
    }

    public boolean hasMine(int row, int col){
        return this.getGameModel().hasMine(row, col);
    }

    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }
    public GameFrame getGameFrame(){
        return this.gameFrame;
    }
    public void setBoardPanel(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
        registerCellMouseHandlers();
    }
    public BoardPanel getBoardPanel(){
        return this.boardPanel;
    }

    public void setScoreBoardPanel(ScoreBoardPanel scoreBoardPanel){
        this.scoreBoardPanel = scoreBoardPanel;
    }
    public ScoreBoardPanel getScoreBoardPanel(){
        return this.scoreBoardPanel;
    }

    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
    public GameModel getGameModel(){
        return this.gameModel;
    }

}
