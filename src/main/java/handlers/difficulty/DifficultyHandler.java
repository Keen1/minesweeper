package handlers.difficulty;

import controllers.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* Difficulty handler
* Handles the actions required to set the board component and game model correctly when the user selects a difficulty
 */

public record DifficultyHandler(GameController gameController, Dimension dimension, int rowCount, int columnCount, int mineCount) implements ActionListener {

    //constructor

    //set the model difficulty and the board panel given the set parameters
    @Override
    public void actionPerformed(ActionEvent event) {

        this.gameController().setModelDifficulty(this.rowCount(), this.columnCount(), this.mineCount());
        this.gameController().setFrameBoardPanel(this.rowCount(), this.columnCount(), this.mineCount(), this.dimension());

    }

}
