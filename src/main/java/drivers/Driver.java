package drivers;

import components.GameFrame;
import javax.swing.*;
import java.awt.*;

/*
* driver class
* entry point for the program*/
public class Driver {

    //main
    public static void main(String[] args){

        setApplicationName();
        //set the game frame and show the frame
        SwingUtilities.invokeLater( () -> {
            GameFrame frame = new GameFrame();
            frame.showFrame();

        });

    }

    public static void setApplicationName(){
        try{
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            java.lang.reflect.Field awtAppClassNameField = toolkit.getClass().getDeclaredField("awtAppClassName");
            awtAppClassNameField.setAccessible(true);
            awtAppClassNameField.set(toolkit, "minesweeper");

        }catch(NoSuchFieldException e){
            System.out.printf("no field name: %s", e.getMessage());
        }catch(IllegalAccessException e){
            System.out.printf("illegal access: %s", e.getMessage());
        }
    }

}
