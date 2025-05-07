package drivers;

import components.GameFrame;

import javax.swing.*;

public class Driver {
    public static void main(String[] args){
        SwingUtilities.invokeLater( () -> {
            GameFrame frame = new GameFrame();
            frame.showFrame();

        });

    }
}
