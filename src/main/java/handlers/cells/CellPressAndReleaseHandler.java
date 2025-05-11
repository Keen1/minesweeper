package handlers.cells;

import components.CellButton;
import controllers.GameController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
* cell press and release handler
* Changes the image of the status button during a press and release of the mouse button
* TODO: will need to adapt this behavior depending on the following:
*   DONE ---
*       if the cell has a mine we need to use the dead face instead of going back to the smile face
*   IN PROGRESS ---
*       we also need to lock the game state so the user has to restart  -- could probably just freeze the board panel?
*       Also need to delineate between a left and a right click, depending on whether user is revealing cell or
*       placing flag...
*
* */
public class CellPressAndReleaseHandler extends MouseAdapter {

    //attributes, thought about not including the status button but would have to do a call chain
    // through the controller or write a function in the controller which seems a little narrow for a function? idk
    private final GameController gameController;

    //constructor
    public CellPressAndReleaseHandler(GameController gameController){
        this.gameController = gameController;
    }



    //override for the mousePressed handling
    //change the image to the "surprised face" when the user presses the mouse on a cell
    @Override
    public void mousePressed(MouseEvent event){
        if(SwingUtilities.isLeftMouseButton(event)){
            this.getGameController().setSurprisedImageIcon();

        }

    }

    //override for the mouseReleased handling
    //change the image to either the "smiling face" or the "dead face" depending on whether the user clicked a bomb
    //or not
    @Override
    public void mouseReleased(MouseEvent event){

        if(SwingUtilities.isLeftMouseButton(event)){

            CellButton source = (CellButton)event.getSource();
            int row = source.getRow();
            int col = source.getColumn();

            //TODO: this is where we want the game state to move to game over
            //
            if(this.getGameController().hasMine(row, col)){
                this.getGameController().setDeadImageIcon();
                this.getGameController().stopTimer();

            }else{
                this.getGameController().setSmilingImageIcon();
            }
        }
    }

    //get the game controller
    public GameController getGameController(){
        return this.gameController;
    }
}
