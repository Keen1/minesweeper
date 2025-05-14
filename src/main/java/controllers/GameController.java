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
import java.awt.event.ActionListener;

/*
* Game Controller
* manages the state of the game and the gui front end
*/

public class GameController {

    //front end components and back end model
    private final GameFrame gameFrame;
    private BoardPanel boardPanel;
    private final ScoreBoardPanel scoreBoardPanel;
    private final GameModel gameModel;

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

    //reset the board given the set parameters
    public void resetBoard(){

        int rows = this.getBoardPanel().getBoardRows();
        int columns = this.getBoardPanel().getBoardColumns();
        int mineCount = this.getBoardPanel().getMineCount();
        Dimension dimension = this.getGameFrame().getSize();

        this.getGameModel().resetBoard();
        this.getGameFrame().changeBoardPanel(rows, columns, mineCount, dimension);
        this.setCellButtonImagePaths();
        this.resetTimer();
        ActionListener[] al = this.getScoreBoardPanel().getTimer().getActionListeners();
        for(ActionListener listener : al){
            System.out.printf(listener.getClass().toString());
        }

        this.updateFlagCounts();
        this.updateMinesFlaggedLabel();
    }

    //check the win status of the game
    public void checkWinStatus(){

        if(this.isGameWon()){

            this.setWinStatus();

        }
    }

    //set the win status of the game
    public void setWinStatus(){
        System.out.println("game won!!!!");
        this.stopTimer();
        this.disableButtonBoard();
        this.setSunglassesImageIcon();

    }

    //set the lose status of the game
    public void setLoseStatus(){

        this.stopTimer();
        this.disableButtonBoard();
        this.setDeadImageIcon();

    }

    //disable the button board on the board panel
    public void disableButtonBoard(){
        this.getBoardPanel().disableButtonPanel();
    }

    //set the difficulty of the gui and the model given the rows, columns, and mine count
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

    //check whether the game has been won or not
    public boolean isGameWon(){
        return this.getGameModel().isGameWon();
    }

    //register the status button handler
    public void registerStatusButtonHandler(){
        this.getScoreBoardPanel().getGameStatusButton().addActionListener(new GameResetHandler(this));
    }

    //register the timer handler
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

    //set the image icon of a cell button to null
    public void setCellButtonImageToNull(int row, int col){
        this.getBoardPanel().setButtonIconToNull(row, col);
    }

    //update the flag counts placed by the user
    public void updateFlagCounts(){
        this.getGameModel().updateFlaggedCount();
        this.getGameModel().updateMinesFlaggedCount();

    }

    //update the mines flagged label TODO: this might need to be a different name? Its not a minesFlagged label its just a counter for number of flags placed
    public void updateMinesFlaggedLabel(){
        int minesFlagged = this.getGameModel().getFlaggedCount();
        this.getScoreBoardPanel().updateMinesFlaggedLabel(Integer.toString(minesFlagged));
    }

    //get the number of rows on the board panel
    public int getBoardPanelRows(){
        return this.getBoardPanel().getBoardRows();
    }

    //get the number of columns on the board panel
    public int getBoardPanelColumns(){
        return this.getBoardPanel().getBoardColumns();
    }

    //set the cell button image paths
    public void setCellButtonImagePaths(){
        this.getBoardPanel().setImagePaths(this.getGameModel());
    }

    //get a cell button given a row and a column
    public CellButton getCellButton(int row, int column){
        return this.getBoardPanel().getCellButton(row, column);
    }

    //set the flag image icon of a cell button given the row and column
    public void setFlagImageIcon(int row, int column){
        this.getBoardPanel().setFlagIcon(row, column);
    }

    //reset the timer
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

    //stop the timer
    public void stopTimer(){
        this.getScoreBoardPanel().stopTimer();
    }

    //set the sunglasses image icon on the status button
    public void setSunglassesImageIcon(){
        this.getScoreBoardPanel().setSunglassesFaceIcon();
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

    //set the user flag for cell given a row and column
    public void setUserFlag(int row, int col, boolean userFlag){
        this.getGameModel().setUserFlag(row, col, userFlag);
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

    //get the scoreboard panel
    public ScoreBoardPanel getScoreBoardPanel(){
        return this.scoreBoardPanel;
    }

    //get the game model
    public GameModel getGameModel(){
        return this.gameModel;
    }

}
