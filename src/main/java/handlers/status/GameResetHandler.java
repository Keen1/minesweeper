package handlers.status;

import controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
* game reset handler
* resets the game when the status button is clicked
*/
public class GameResetHandler implements ActionListener {
    //controller variable
    private final GameController gameController;

    //constructor
    public GameResetHandler(GameController gameController){
        this.gameController = gameController;
    }

    //override for action performed
    //reset the board and set the image icon
    @Override
    public void actionPerformed(ActionEvent event){
        this.getController().resetBoard();
        this.getController().setSmilingImageIcon();
    }

    //get the controller
    public GameController getController(){
        return this.gameController;
    }
}
