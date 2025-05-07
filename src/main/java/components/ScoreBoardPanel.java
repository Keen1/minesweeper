package components;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardPanel extends JPanel{
    private JLabel minesRemainingLabel;
    private JLabel secondsLabel;
    private JButton gameStatusButton;

    private Timer timer;

    int seconds = 0;

    public ScoreBoardPanel(){
        initComponents();
    }

    private void initComponents(){

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(getSecondsLabel());
        add(getGameStatusButton());
        add(getMinesRemainingLabel());

        setVisible(true);

    }

    private void initTimer(){
        //TODO: update the action listener here instead of using null
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
        this.minesRemainingLabel = new JLabel("0");

    }
    public JLabel getMinesRemainingLabel(){
        if(this.minesRemainingLabel == null){
            initMinesRemainingLabel();
        }
        return this.minesRemainingLabel;
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
