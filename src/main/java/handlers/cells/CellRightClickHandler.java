package handlers.cells;

import controllers.GameController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellRightClickHandler extends MouseAdapter {
    private GameController gameController;
    public CellRightClickHandler(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public void mouseClicked(MouseEvent event){

    }


    public GameController getGameController(){
        return this.gameController;
    }
}
