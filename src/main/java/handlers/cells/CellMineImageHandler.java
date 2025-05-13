package handlers.cells;

import components.CellButton;
import controllers.GameController;
import utils.NumberIconPathVars;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

/*
* mine image handler that handles displaying a mine image if the cell clicked contains a mine
* */
public class CellMineImageHandler extends MouseAdapter {
    //controller attribute
    private final GameController gameController;
    //static mine and flag  image paths
    private static final String MINE_PATH = "/images/mine.png";
    private static final String FLAG_PATH = "/images/red_flag_24.png";
    //static timer started boolean
    private static boolean timeStarted;

    //constructor
    public CellMineImageHandler(GameController gameController){

        this.gameController = gameController;
        timeStarted = false;

    }
    //determine if the timer has started
    public boolean timeHasStarted(){
        return timeStarted;
    }

    //set the status of the timer
    public void setTimeStarted(boolean started){
        timeStarted = started;
    }

    //override for mouseClicked event
    @Override
    public void mouseClicked(MouseEvent event) {

        //if the timer hasn't started, set the timer status boolean and start the component
        if(!timeHasStarted()){

            setTimeStarted(true);
            this.getGameController().startTimer();

        }
        //get the source, row, and column
        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int column = source.getColumn();


        URL url = null;
        ImageIcon image;
        //check if the click was a left or right click to determine if user was setting a flag
        if(SwingUtilities.isRightMouseButton(event)){

            url = getClass().getResource(FLAG_PATH);

        }else if(SwingUtilities.isLeftMouseButton(event)) {

            //check if the cell has a mine
            if(this.getGameController().hasMine(row, column)) {

                url = getClass().getResource(MINE_PATH);


            }else{

                //determine number of adjacent mines
                int adjacentMines = this.getGameController().getAdjacentMines(row, column);

                if (adjacentMines > 0) {
                    String adjMineImgPath = NumberIconPathVars.getPathFromInt(adjacentMines);
                    url = getClass().getResource(adjMineImgPath);
                }


            }
        }

        //init the image and set the cell's image icon
        if(url != null){
            image = new ImageIcon(Objects.requireNonNull(url));
            SwingUtilities.invokeLater(()->{
                this.getGameController().setCellButtonImageIcon(source, image);

                    });

        }

        this.getGameController().doFloodFill(row , column );
    }

    //get the game controller
    public GameController getGameController(){
        return this.gameController;
    }
}
