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
*   if the cell has a mine we need to use the dead face instead of going back to the smile face
*   we also need to lock the game state so the user has to restart  -- could probably just freeze the board panel?
*
* */
public class CellPressAndReleaseHandler extends MouseAdapter {

    private final JButton statusButton;
    private final GameController gameController;

    private static final String ON_THE_LINE_PATH = "/images/surprised_face.png";
    private static final String ALIVE_PATH = "/images/smiling_face.png";
    private static final String DEAD_PATH = "/images/dead_face.png";

    public CellPressAndReleaseHandler(JButton statusButton, GameController gameController){
        this.statusButton = statusButton;
        this.gameController = gameController;
    }



    @Override
    public void mousePressed(MouseEvent event){
        //load the image
        URL clickedImageURL= getClass().getResource(ON_THE_LINE_PATH);
        ImageIcon clickedImageIco = new ImageIcon(Objects.requireNonNull(clickedImageURL));
        this.getStatusButton().setIcon(clickedImageIco);

    }

    @Override
    public void mouseReleased(MouseEvent event){
        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int col = source.getColumn();
        ImageIcon image;
        URL url;
        if(this.getGameController().hasMine(row, col)){
            url = getClass().getResource(DEAD_PATH);
            image = new ImageIcon(Objects.requireNonNull(url));

        }else{
            url = getClass().getResource(ALIVE_PATH);
            image = new ImageIcon(Objects.requireNonNull(url));

        }
        this.getStatusButton().setIcon(image);
    }



    public JButton getStatusButton(){
        return this.statusButton;
    }

    public GameController getGameController(){
        return this.gameController;
    }
}
