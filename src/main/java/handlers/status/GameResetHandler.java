package handlers.status;

import controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameResetHandler implements ActionListener {
    private final GameController gameController;

    public GameResetHandler(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        this.getController().resetBoard();
    }

    public GameController getController(){
        return this.gameController;
    }
}
