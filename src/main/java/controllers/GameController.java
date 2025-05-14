package controllers;

import components.BoardPanel;
import components.CellButton;
import components.GameFrame;
import components.ScoreBoardPanel;
import handlers.cells.CellClickHandler;
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

    public void resetBoard(){
        int rows = this.getBoardPanel().getBoardRows();
        int columns = this.getBoardPanel().getBoardColumns();
        int mineCount = this.getBoardPanel().getMineCount();
        Dimension dimension = this.getGameFrame().getSize();

        this.getGameModel().resetBoard();
        this.getGameFrame().changeBoardPanel(rows, columns, mineCount, dimension);
        this.setCellButtonImagePaths();
        this.resetTimer();
    }

    public void disableButtonBoard(){
        this.getBoardPanel().disableButtonPanel();
    }
    public void enableButtonBoard(){
        this.getBoardPanel().enableButtonPanel();
    }

    //set the difficulty of the gui and the model give nthe rows, columns, and mine count
    public void setModelDifficulty(int row, int col, int mineCount){
        this.getGameModel().resetBoard(row, col, mineCount);
        this.getBoardPanel().changeBoardSize(row, col, mineCount);
        this.getBoardPanel().setImagePaths(this.getGameModel());
    }

    //set the new frame board panel
    public void setFrameBoardPanel(int row, int col, int mineCount, Dimension dimension){
        this.getGameFrame().changeBoardPanel(row, col, mineCount, dimension);
        this.getBoardPanel().setImagePaths(this.getGameModel());
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
                cell.addMouseListener(new CellClickHandler(this));
            }
        }
    }

    public void setCellButtonImageToNull(int row, int col){
        this.getBoardPanel().setButtonIconToNull(row, col);
    }

    //TODO: not functioning correctly
    public void doFloodFill(int row, int col){

        if(row < 0 || row >= this.getBoardPanel().getBoardRows() || col < 0 || col >= this.getBoardPanel().getBoardColumns()){
            return;
        }

        CellButton cell = this.getBoardPanel().getCellButton(row, col);

        if(cell.isLockSelected()){
            return;
        }
        if(this.getGameModel().hasMine(row, col)){
            return;
        }

        cell.doClick();


        int adjacentMines = this.getAdjacentMines(row, col);

        if(adjacentMines == 0){
            doFloodFill(row - 1, col - 1);
            doFloodFill(row - 1, col);
            doFloodFill(row - 1, col + 1);
            doFloodFill(row, col - 1);
            doFloodFill(row, col + 1);
            doFloodFill(row + 1, col - 1);
            doFloodFill(row + 1, col);
            doFloodFill(row + 1, col + 1);
        }



    }

    public void setCellButtonImagePaths(){
        this.getBoardPanel().setImagePaths(this.getGameModel());
    }

    public CellButton getCellButton(int row, int column){
        return this.getBoardPanel().getCellButton(row, column);
    }

    public void setCellButtonImageIcon(CellButton button, ImageIcon image){
        this.getBoardPanel().setCellImageIcon(button, image);
    }

    public void setFlagImageIcon(int row, int column){
        this.getBoardPanel().setFlagIcon(row, column);
    }

    public void resetTimer(){
        this.getScoreBoardPanel().resetTimer();
        this.getScoreBoardPanel().stopTimer();
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

    public boolean hasUserFlag(int row, int col){
        return this.getGameModel().hasUserFlag(row, col);
    }
    public void setUserFlag(int row, int col, boolean userFlag){
        this.getGameModel().setUserFlag(row, col, userFlag);
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
