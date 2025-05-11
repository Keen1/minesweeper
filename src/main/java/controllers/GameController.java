package controllers;

import components.BoardPanel;
import components.CellButton;
import components.GameFrame;
import components.ScoreBoardPanel;
import handlers.cells.CellMineImageHandler;
import handlers.cells.CellPressAndReleaseHandler;
import handlers.status.GameResetHandler;
import handlers.timer.TimerUpdateHandler;
import models.GameModel;

import javax.swing.*;
import java.awt.*;

/*
* Game Controller
* manages the state of the game and the gui front end
*/
public class GameController {

    //front end components and back end model
    private GameFrame gameFrame;
    private BoardPanel boardPanel;
    private ScoreBoardPanel scoreBoardPanel;
    private GameModel gameModel;

    //constructor
    public GameController(GameFrame gameFrame, BoardPanel boardPanel, ScoreBoardPanel scoreBoardPanel, GameModel gameModel){
        this.gameFrame = gameFrame;
        this.boardPanel = boardPanel;
        this.scoreBoardPanel = scoreBoardPanel;
        this.gameModel = gameModel;
        registerCellMouseHandlers();
        registerTimerHandler();
        registerStatusButtonHandler();
    }

    //disable the board panel. TODO: Doesn't work as intended
    public void disableBoardPanel(){
        this.disablePanel(this.getBoardPanel());
    }

    //disable any panel and its components passed. TODO Doesn't work as intended
    private void disablePanel(JPanel panel){
        panel.setEnabled(false);
        for(Component component : panel.getComponents()){
            component.setEnabled(false);

            if(component instanceof Container){
                disableComponents((Container)component);
            }
        }
    }

    //disable any components associated with a container. TODO Doesn't work as intended
    private void disableComponents(Container container){
        for(Component component : container.getComponents()){
            component.setEnabled(false);

            if(component instanceof Container){
                disableComponents((Container)component);
            }
        }
    }

    public void resetBoard(){
        this.getGameModel().resetBoard();
        this.getBoardPanel().resetPanel();
        this.resetTimer();
    }

    //set the difficulty of the gui and the model give nthe rows, columns, and mine count
    public void setModelDifficulty(int row, int col, int mineCount){
        this.getGameModel().resetBoard(row, col, mineCount);
        this.getBoardPanel().changeBoardSize(row, col, mineCount);
    }

    //set the new frame board panel
    public void setFrameBoardPanel(int row, int col, int mineCount, Dimension dimension){
        this.getGameFrame().changeBoardPanel(row, col, mineCount, dimension);
    }

    public void registerStatusButtonHandler(){
        this.getScoreBoardPanel().getGameStatusButton().addActionListener(new GameResetHandler(this));
    }

    public void registerTimerHandler(){
        this.getScoreBoardPanel().getTimer().addActionListener(new TimerUpdateHandler(this));
    }

    //register mouse handlers on cell buttons
    public void registerCellMouseHandlers(){
        JToggleButton[][] cellButtons = this.getBoardPanel().getCellButtons();
        for (JToggleButton[] cellRow : cellButtons) {
            for (JToggleButton cell : cellRow) {
                cell.addMouseListener(new CellPressAndReleaseHandler(this));
                cell.addMouseListener(new CellMineImageHandler(this));
            }
        }
    }

    public void setCellButtonImageIcon(CellButton button, ImageIcon image){
        this.getBoardPanel().setCellImageIcon(button, image);
    }

    public void resetTimer(){
        this.getScoreBoardPanel().resetTimer();
    }

    //update the timer for the scoreboard
    public void updateTimer(){
        this.getScoreBoardPanel().updateSeconds();
    }

    //start the timer for the scoreboard
    public void startTimer(){
        this.getScoreBoardPanel().startTimer();
    }

    public void stopTimer(){
        this.getScoreBoardPanel().stopTimer();
    }

    //set the surprised image icon on the status button
    public void setSurprisedImageIcon(){
        this.getScoreBoardPanel().setSurprisedFaceIcon();
    }

    //set the smiling image icon on the status button
    public void setSmilingImageIcon(){
        this.getScoreBoardPanel().setSmilingFaceIcon();
    }

    //set the dead image icon on the status button
    public void setDeadImageIcon(){
        this.getScoreBoardPanel().setDeadFaceIcon();
    }

    //get the number of adjacent mines given a cell's row and column
    public int getAdjacentMines(int row, int col){
        return this.getGameModel().getAdjacentMinesCount(row, col);
    }

    //determine if a cell has a mine given its row and column
    public boolean hasMine(int row, int col){
        return this.getGameModel().hasMine(row, col);
    }

    //set the game frame
    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    //get the game frame
    public GameFrame getGameFrame(){
        return this.gameFrame;
    }

    //set the board panel
    public void setBoardPanel(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
        registerCellMouseHandlers();
    }

    //get the board panel
    public BoardPanel getBoardPanel(){
        return this.boardPanel;
    }

    //set the scoreboard panel
    public void setScoreBoardPanel(ScoreBoardPanel scoreBoardPanel){
        this.scoreBoardPanel = scoreBoardPanel;
    }

    //get the scoreboard panel
    public ScoreBoardPanel getScoreBoardPanel(){
        return this.scoreBoardPanel;
    }

    //set the game model
    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }

    //get the game model
    public GameModel getGameModel(){
        return this.gameModel;
    }

}
