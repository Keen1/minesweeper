package handlers.cells;

import components.CellButton;
import controllers.GameController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
* Cell click handler
* handles user clicks on cell buttons on the board panel
* determines if a mine is at the cell clicked and calls the loadImage function
* also handles left and right clicks, loading the mine flag if user does a right click
* */

public class CellClickHandler extends MouseAdapter {
    //controller attribute
    private final GameController gameController;

    private static boolean timeStarted;

    //constructor
    public CellClickHandler(GameController gameController){
        this.gameController = gameController;
        timeStarted = false;
    }

    //override for mouse pressed
    //only used to check if the user is placing a flag(right click)
    @Override
    public void mousePressed(MouseEvent event){

        if(SwingUtilities.isLeftMouseButton(event)){

            this.getGameController().setSurprisedImageIcon();

        }
    }

    //override for mouse released
    //checks for left or right click to determine of we need to load a flag image
    //if not placing a flag load the button's image and then check the model for a mine.
    //if there is a mine freeze the game state, otherwise reset the status button icon.
    @Override
    public void mouseReleased(MouseEvent event){

        if(!this.hasTimeStarted()){
            setTimeStarted(true);
            this.getGameController().startTimer();
        }

        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int col = source.getColumn();

        if(SwingUtilities.isRightMouseButton(event)){

            if(source.isUserFlagged()){

                source.setUserFlagged(false);
                this.getGameController().setUserFlag(row, col, false);
                this.getGameController().setCellButtonImageToNull(row, col);
            }else{
                this.getGameController().setFlagImageIcon(row, col);
                source.setUserFlagged(true);
                this.getGameController().setUserFlag(row, col, true);

            }


        }else{
            if(!source.isUserFlagged()){

                source.loadImage();

                if(this.getGameController().hasMine(row, col)){

                    this.getGameController().setDeadImageIcon();
                    this.getGameController().stopTimer();
                    this.getGameController().disableButtonBoard();

                }else{

                    this.getGameController().setSmilingImageIcon();
                }
            }


        }
    }

    //getter for the game controller
    public GameController getGameController(){
        return this.gameController;
    }

    public boolean hasTimeStarted(){
        return timeStarted;
    }

    public void setTimeStarted(boolean b){
        timeStarted = b;
    }
}
