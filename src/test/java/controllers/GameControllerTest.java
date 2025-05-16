package controllers;

import components.GameFrame;
import org.junit.jupiter.api.BeforeAll;

public class GameControllerTest {
    private static GameFrame gameFrame;

    @BeforeAll
    public static void setup(){
        gameFrame = new GameFrame();
    }



    public void resetFrame(){
        gameFrame = new GameFrame();
    }


}
