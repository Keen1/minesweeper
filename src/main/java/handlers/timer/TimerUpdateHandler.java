package handlers.timer;

import controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* timer update handler
* updates the timer label every 1000ms (1sec) as defined by the timer instantiated in the scoreboard panel
*/
public class TimerUpdateHandler implements ActionListener {
    private final GameController gameController;

    public TimerUpdateHandler(GameController gameController){
        this.gameController = gameController;
    }

    //constructor


    //every tick of the timer call the update function in the controller
    @Override
    public void actionPerformed(ActionEvent event) {
        this.getGameController().updateTimer();
    }


    public GameController getGameController(){
        return this.gameController;
    }

}
