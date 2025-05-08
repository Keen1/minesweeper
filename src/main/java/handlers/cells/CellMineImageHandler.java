package handlers.cells;

import components.CellButton;
import controllers.GameController;
import utils.PathVars;

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
    //static mine image path
    private static final String MINE_PATH = "/images/mine.png";
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

        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int column = source.getColumn();
        URL url = null;
        ImageIcon image;

        if(this.getGameController().hasMine(row, column)){
            url = getClass().getResource(MINE_PATH);

        }else{

            int adjacentMines = this.getGameController().getAdjacentMines(row, column);

            if(adjacentMines > 0){
                String adjMineImgPath = PathVars.getPathFromInt(adjacentMines);
                url = getClass().getResource(adjMineImgPath);
            }


        }

        if(url != null){
            image = new ImageIcon(Objects.requireNonNull(url));
            this.getGameController().setCellButtonImageIcon(source, image);

        }
    }


    public GameController getGameController(){
        return this.gameController;
    }
}
