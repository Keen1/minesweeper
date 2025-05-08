package handlers.cells;

import components.CellButton;
import controllers.GameController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

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
    private final JButton statusButton;
    private final GameController gameController;

    //constants for image paths
    private static final String ON_THE_LINE_PATH = "/images/surprised_face.png";
    private static final String ALIVE_PATH = "/images/smiling_face.png";
    private static final String DEAD_PATH = "/images/dead_face.png";

    //constructor
    public CellPressAndReleaseHandler(JButton statusButton, GameController gameController){
        this.statusButton = statusButton;
        this.gameController = gameController;
    }



    //override for the mousePressed handling
    //change the image to the "surprised face" when the user presses the mouse on a cell
    @Override
    public void mousePressed(MouseEvent event){
        //load the image
        URL clickedImageURL= getClass().getResource(ON_THE_LINE_PATH);
        ImageIcon clickedImageIco = new ImageIcon(Objects.requireNonNull(clickedImageURL));
        this.getStatusButton().setIcon(clickedImageIco);

    }

    //override for the mouseReleased handling
    //change the image to either the "smiling face" or the "dead face" depending on whether the user clicked a bomb
    //or not
    @Override
    public void mouseReleased(MouseEvent event){

        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int col = source.getColumn();

        ImageIcon image;
        URL url;

        if(this.getGameController().hasMine(row, col)){
            url = getClass().getResource(DEAD_PATH);

        }else{
            url = getClass().getResource(ALIVE_PATH);
        }

        image = new ImageIcon(Objects.requireNonNull(url));
        this.getStatusButton().setIcon(image);

    }


    //get the status button
    public JButton getStatusButton(){
        return this.statusButton;
    }

    //get the game controller
    public GameController getGameController(){
        return this.gameController;
    }
}
