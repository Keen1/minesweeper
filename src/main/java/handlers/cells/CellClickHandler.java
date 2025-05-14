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

        //check if the timer is started
        if(!this.hasTimeStarted()){

            setTimeStarted(true);
            this.getGameController().startTimer();

        }

        //get the source button for the event and its corresponding row and column
        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int col = source.getColumn();

        //check if the user is setting or removing a flag
        if(SwingUtilities.isRightMouseButton(event)){

            //if the button is flagged the user is removing the flag
            if(source.isUserFlagged()){

                //set flag booleans to false and update the button image
                source.setUserFlagged(false);
                this.getGameController().setUserFlag(row, col, false);
                this.getGameController().setCellButtonImageToNull(row, col);

            //the button is not flagged and the user is adding a flag
            }else{

                //set the flag booleans to true and update the button image
                source.setUserFlagged(true);
                this.getGameController().setUserFlag(row, col, true);
                this.getGameController().setFlagImageIcon(row, col);

            }
            this.getGameController().updateFlagCounts();
            this.getGameController().updateMinesFlaggedLabel();

        //left click means user wants to reveal the cell
        }else{

            //don't reveal the cell if the user has flagged it
            if(!source.isUserFlagged()){

                //load the button's image
                source.loadImage();

                //check for mine
                if(this.getGameController().hasMine(row, col)){
                    //game is over, set status button icon, stop timer, and disable the button board
                    this.getGameController().setDeadImageIcon();
                    this.getGameController().stopTimer();
                    this.getGameController().disableButtonBoard();

                }else{
                    if(this.getGameController().getAdjacentMines(row, col) == 0){
                        for(int i = row - 1; i <= row + 1; i++){
                            for(int j = col - 1; j <= col + 1; j++){
                                if(i == row && j == col){
                                    continue;
                                }
                                floodFillFrom(i, j);
                            }
                        }
                    }
                    //update the status icon
                    this.getGameController().setSmilingImageIcon();


                }
            }


        }



    }

    private void floodFillFrom(int row, int col){
        if(row < 0 || row >= this.getGameController().getBoardPanelRows() ||
            col < 0 || col >= this.getGameController().getBoardPanelColumns()){
            return;
        }
        CellButton currentCell = this.getGameController().getCellButton(row, col);

        if(currentCell.isSelected() || currentCell.isUserFlagged() || this.getGameController().hasMine(row, col)){
            return;
        }

        currentCell.setSelected(true);
        currentCell.loadImage();

        if(this.getGameController().getAdjacentMines(row, col ) > 0){
            return;
        }

        for(int i = row - 1; i <= row + 1; i++){
            for(int j = col - 1; j <= col + 1; j++){

                if(i == row && j == col){
                    continue;
                }

                floodFillFrom(i, j);

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
