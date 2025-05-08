package handlers.cells;

import components.CellButton;
import controllers.GameController;
import utils.PathVars;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

public class CellMineImageHandler extends MouseAdapter {
    private final GameController gameController;

    private static final String MINE_PATH = "/images/mine.png";

    private static boolean timeStarted;

    public CellMineImageHandler(GameController gameController){
        this.gameController = gameController;
        timeStarted = false;
    }

    public boolean timeHasStarted(){
        return timeStarted;
    }
    public void setTimeStarted(boolean started){
        timeStarted = started;
    }


    @Override
    public void mouseClicked(MouseEvent event) {
        if(!timeHasStarted()){
            setTimeStarted(true);
            this.getGameController().startTimer();

        }
        CellButton source = (CellButton)event.getSource();
        int row = source.getRow();
        int column = source.getColumn();
        if(this.getGameController().hasMine(row, column)){

            URL mineURL = getClass().getResource(MINE_PATH);
            ImageIcon mineImage = new ImageIcon(Objects.requireNonNull(mineURL));
            source.setIcon(mineImage);
        }else{

            int adjacentMines = this.getGameController().getAdjacentMines(row, column);
            if(adjacentMines > 0){
                String adjMineImgPath = PathVars.getPathFromInt(adjacentMines);
                URL adjMineURL = getClass().getResource(adjMineImgPath);
                ImageIcon adjMineDigitImg = new ImageIcon(Objects.requireNonNull(adjMineURL));
                source.setIcon(adjMineDigitImg);
            }


        }
    }


    public GameController getGameController(){
        return this.gameController;
    }
}
