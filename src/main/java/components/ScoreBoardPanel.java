package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreBoardPanel extends JPanel{
    private JLabel minesRemaining;
    private JLabel secondsLabel;
    private JButton gameStatusButton;

    private Timer timer;

    int seconds = 0;

    public ScoreBoardPanel(){

    }

    private void initComponents(){
        setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    private void initTimer(){
        this.timer = new Timer(1000, null);
    }
    public Timer getTimer(){
        if(this.timer == null){
            initTimer();
        }
        return this.timer;
    }


    private void initSecondsLabel(){
        this.secondsLabel = new JLabel("0");

    }

    public JLabel getSecondsLabel(){
        if(this.secondsLabel == null){
            initSecondsLabel();
        }
        return this.secondsLabel;
    }
    private void updateSecondsLabel(int seconds){
        this.setSecondsLabel(Integer.toString(seconds));
    }
    public void setSecondsLabel(String seconds){
        this.secondsLabel.setText(seconds);
    }




    private void initMinesRemainingLabel(){
        this.minesRemaining = new JLabel("0");

    }
    public JLabel getMinesRemainingLabel(){
        if(this.minesRemaining == null){
            initMinesRemainingLabel();
        }
        return this.minesRemaining;
    }

    private void initGameStatusButton(){
        this.gameStatusButton = new JButton("status");
    }
    public JButton getGameStatusButton(){
        if(this.gameStatusButton == null){
            initGameStatusButton();
        }
        return this.gameStatusButton;
    }


}
