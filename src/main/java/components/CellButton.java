package components;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.Objects;

/*
*Cell button for front end. Tracks the location of the button via the row and column attribute while maintaining
* JButton behavior through extension
*/
public class CellButton extends JToggleButton {

    //attributes
    private final int row;
    private final int column;

    private boolean lockSelected = false;
    private boolean userFlagged = false;

    private String imagePathString;

    //constructor
    public CellButton(int row, int column){

        this.row = row;
        this.column = column;
        setSelectionBehavior();


    }


    public CellButton(int row, int column, String imagePathString){
        this.row = row;
        this.column = column;
        this.imagePathString = imagePathString;
        setSelectionBehavior();
    }

    public String getImagePathString(){
        return this.imagePathString;
    }

    public void setImagePathString(String imagePathString){
        this.imagePathString = imagePathString;
    }

    public void loadImage(){
        if(this.getImagePathString() != null &&!this.getImagePathString().isEmpty()){
            URL url = getClass().getResource(getImagePathString());
            ImageIcon image = new ImageIcon(Objects.requireNonNull(url));
            SwingUtilities.invokeLater(() ->{
                setIcon(image);
            });
        }

    }


    //get the row of the button
    public int getRow(){
        return this.row;
    }

    //bet the column of the button
    public int getColumn(){
        return this.column;
    }

    public boolean isUserFlagged(){
        return this.userFlagged;
    }

    public void setUserFlagged(boolean userFlagged){
        this.userFlagged = userFlagged;
    }

    public boolean isLockSelected(){
        return this.lockSelected;
    }

    public void setLockSelected(boolean lockSelected){
        this.lockSelected = lockSelected;
    }

    private void setSelectionBehavior(){
        addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(isLockSelected() && e.getStateChange() == ItemEvent.DESELECTED){
                    SwingUtilities.invokeLater(() -> setSelected(true));
                }else if(e.getStateChange() == ItemEvent.SELECTED){
                    setLockSelected(true);
                }
            }
        });
    }



}
