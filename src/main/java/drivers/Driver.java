package drivers;

import components.GameFrame;
import javax.swing.*;

/*
* driver class
* entry point for the program*/
public class Driver {

    //main
    public static void main(String[] args){

        //set the game frame and show the frame
        SwingUtilities.invokeLater( () -> {
            GameFrame frame = new GameFrame();
            frame.showFrame();

        });

    }

}
