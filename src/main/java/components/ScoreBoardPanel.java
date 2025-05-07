package components;

import javax.swing.*;

public class ScoreBoardPanel {
    private JLabel flagsSet;
    private JLabel minesRemaining;
    private JButton gameStatusButton;

    public ScoreBoardPanel(){

    }

    public void initFlagsSetLabel(){
        this.flagsSet = new JLabel("0");

    }
    public JLabel getFlagsSetLabel(){
        if(this.flagsSet == null){
            initFlagsSetLabel();
        }
        return this.flagsSet;
    }

    public void initMinesRemainingLabel(){
        this.minesRemaining = new JLabel("0");

    }
    public JLabel getMinesRemainingLabel(){
        if(this.minesRemaining == null){
            initMinesRemainingLabel();
        }
        return this.minesRemaining;
    }

    public void initGameStatusButton(){
        this.gameStatusButton = new JButton("status");
    }
    public JButton getGameStatusButton(){
        if(this.gameStatusButton == null){
            initGameStatusButton();
        }
        return this.gameStatusButton;
    }


}
