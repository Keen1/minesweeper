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

    //time started variable to track timer status
    private static boolean timeStarted = false;

    //constructor
    public CellClickHandler(GameController gameController){
        this.gameController = gameController;
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

            //update the flag counts
            this.getGameController().updateFlagCounts();
            this.getGameController().updateMinesFlaggedLabel();
            this.getGameController().checkWinStatus();


        //left click means user wants to reveal the cell
        }else{

            //don't reveal the cell if the user has flagged it
            if(!source.isUserFlagged()){

                //load the button's image
                source.loadImage();

                //check for mine
                if(this.getGameController().hasMine(row, col)){

                    //game is over, set the lose status in the controller
                    this.getGameController().setLoseStatus();

                //do flood fill
                }else{

                    //if the clicked button has no adjacent minds iterate through the adjacent buttons and flood fill them
                    if(this.getGameController().getAdjacentMines(row, col) == 0){

                        for(int i = row - 1; i <= row + 1; i++){
                            for(int j = col - 1; j <= col + 1; j++){

                                //if we are at the current button continue
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

    //run the flood fill algorithm from the given button
    private void floodFillFrom(int row, int col){

        //check the bounds
        if(row < 0 || row >= this.getGameController().getBoardPanelRows() ||
            col < 0 || col >= this.getGameController().getBoardPanelColumns()){
            return;
        }

        //get the current cell
        CellButton currentCell = this.getGameController().getCellButton(row, col);

        //if the button is already selected, has a flag, or has a mine, don't show it
        if(currentCell.isSelected() || currentCell.isUserFlagged() || this.getGameController().hasMine(row, col)){
            return;
        }

        //set the button's status to selected and load the button's image
        currentCell.setSelected(true);
        currentCell.loadImage();

        //if the button has adjacent mines stop the flood fill
        if(this.getGameController().getAdjacentMines(row, col ) > 0){
            return;
        }

        //flood fill for adjacent cells
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

    //check if the timer has started
    public boolean hasTimeStarted(){
        return timeStarted;
    }

    //set the timer status
    public void setTimeStarted(boolean b){
        timeStarted = b;
    }
}
