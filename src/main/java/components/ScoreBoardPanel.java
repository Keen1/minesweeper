package components;

import javax.swing.*;

public class ScoreBoardPanel {
    private JLabel flagsSet;
    private JLabel bombsRemaining;
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

    public void initBombsRemainingLabel(){
        this.bombsRemaining = new JLabel("0");

    }
    public JLabel getBombsRemainingLabel(){
        if(this.bombsRemaining == null){
            initBombsRemainingLabel();
        }
        return this.bombsRemaining;
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
