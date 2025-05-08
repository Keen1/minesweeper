package handlers.timer;

import controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* timer update handler
* updates the timer label every 1000ms (1sec) as defined by the timer instantiated in the scoreboard panel
*/

public class TimerUpdateHandler implements ActionListener {

    //attributes
    private final GameController gameController;

    //constructor
    public TimerUpdateHandler(GameController gameController){
        this.gameController = gameController;
    }


    //every tick of the timer call the update function in the controller
    @Override
    public void actionPerformed(ActionEvent event){
        this.getGameController().updateTimer();
    }

    //get the controller
    public GameController getGameController(){
        return this.gameController;
    }

}
