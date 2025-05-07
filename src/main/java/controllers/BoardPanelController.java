package controllers;

import components.BoardPanel;

public class BoardPanelController {
    private BoardPanel boardPanel;
    public BoardPanelController(){

    }
    public BoardPanelController(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
    }




    public BoardPanel getBoardPanel(){
        return this.boardPanel;
    }
    public void setBoardPanel(BoardPanel boardPanel){
        this.boardPanel = boardPanel;
    }
}
