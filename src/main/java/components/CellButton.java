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

    //variables for row and column
    private final int row;
    private final int column;

    //boolean vars for locking the selection status of a cell button and the status of a user flag
    private boolean lockSelected = false;
    private boolean userFlagged = false;

    //each button gets a string for its corresponding image
    private String imagePathString;

    //constructor
    public CellButton(int row, int column){

        this.row = row;
        this.column = column;
        setSelectionBehavior();


    }

    //get the image path string
    public String getImagePathString(){
        return this.imagePathString;
    }

    //set the image path string
    public void setImagePathString(String imagePathString){
        this.imagePathString = imagePathString;
    }

    //load the corresponding image to the button
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

    //get boolean to determine if the cell is flagged by the user
    public boolean isUserFlagged(){
        return this.userFlagged;
    }

    //set the user flag for the cell
    public void setUserFlagged(boolean userFlagged){
        this.userFlagged = userFlagged;
    }

    //get the boolean to determine if the cell is lock selected
    public boolean isLockSelected(){
        return this.lockSelected;
    }

    //set the lock selected boolean
    public void setLockSelected(boolean lockSelected){
        this.lockSelected = lockSelected;
    }

    //set the selection behavior for the button.
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
