package handlers.timer;

import controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerUpdateHandler implements ActionListener {

    private final GameController gameController;

    public TimerUpdateHandler(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        this.getGameController().updateTimer();
    }

    public GameController getGameController(){
        return this.gameController;
    }

}
