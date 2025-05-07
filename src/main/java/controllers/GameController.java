package controllers;

import components.BoardPanel;
import components.GameFrame;
import components.ScoreBoardPanel;
import handlers.CellClickedHandler;
import models.GameModel;

import javax.swing.*;

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

    public void registerCellMouseHandlers(){
        JButton statusButton = this.getScoreBoardPanel().getGameStatusButton();
        JButton[][] cellButtons = this.getBoardPanel().getCellButtons();
        for (JButton[] cellRow : cellButtons) {
            for (JButton cell : cellRow) {
                cell.addMouseListener(new CellClickedHandler(statusButton));
            }
        }
    }

    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }
    public GameFrame getGameFrame(){
        return this.gameFrame;
    }
    public void setBoardPanel(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
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
