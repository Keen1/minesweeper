package controllers;

import models.GameModel;

public class GameController {
    private final GameModel model;

    public GameController(){

    }
    public GameController(GameModel model){
        this.model = model;
    }

    public GameModel getModel(){
        return this.model;
    }


}
