package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

/*
* Scoreboard panel
* tracks the scoreboard and the game status through the status button
*/

public class ScoreBoardPanel extends JPanel{

    //static paths for the status button icons
    private static final String DEAD_FACE_PATH = "/images/dead_face.png";
    private static final String SMILING_FACE_PATH = "/images/smiling_face.png";
    private static final String SUNGLASSES_FACE_PATH = "/images/sunglasses_face.png";
    private static final String SURPRISED_FACE_PATH = "/images/surprised_face.png";

    //components
    private JLabel minesRemainingLabel;
    private JLabel secondsLabel;
    private JButton gameStatusButton;

    //timer for seconds label
    private Timer timer;
    private int seconds = 0;

    private boolean gameOver;

    //constructor
    public ScoreBoardPanel(){
        initComponents();
        this.gameOver = false;
    }

    //init the components of the panel
    private void initComponents(){

        setLayout(new BorderLayout());
        add(getSecondsLabel(), BorderLayout.EAST);
        JPanel buttonWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapperPanel.add(getGameStatusButton());
        add(buttonWrapperPanel, BorderLayout.CENTER);
        add(getMinesRemainingLabel(), BorderLayout.WEST);

        setVisible(true);

    }

    //start the timer
    public void startTimer(){
        this.getTimer().start();
    }

    public void stopTimer(){
        this.getTimer().stop();
    }

    //set the icon for the game status button to the smiling face icon
    public void setSmilingFaceIcon(){

        URL url = getClass().getResource(SMILING_FACE_PATH);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(url));
        this.getGameStatusButton().setIcon(icon);

    }

    //set the icon for the game status button to the smiling face icon
    public void setSurprisedFaceIcon(){

        URL url = getClass().getResource(SURPRISED_FACE_PATH);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(url));
        this.getGameStatusButton().setIcon(icon);

    }

    //set the icon for the game status button to the dead face icon
    public void setDeadFaceIcon(){

        URL url = getClass().getResource(DEAD_FACE_PATH);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(url));
        this.getGameStatusButton().setIcon(icon);

    }

    //load a specific image
    //TODO -- do we need this? Where is this used and why?
    public ImageIcon loadImage(String path){
        URL imgURL = getClass().getResource(path);
        return new ImageIcon(Objects.requireNonNull(imgURL));

    }
    //init the timer
    private void initTimer(){
        //TODO: update the action listener here instead of using null
        // this is updated in the controller but it might be best to set the listener here?
        this.timer = new Timer(1000, null);
    }

    //get the timer
    public Timer getTimer(){
        if(this.timer == null){
            initTimer();
        }
        return this.timer;
    }

    public void resetTimer(){
        this.timer.restart();
        this.setSeconds(0);
        this.updateSecondsLabel();
    }

    //update the seconds variable
    public void updateSeconds(){
        int seconds = this.getSeconds();
        seconds++;
        this.setSeconds(seconds);
        this.updateSecondsLabel();
    }

    //get the current seconds count
    public int getSeconds(){
        return this.seconds;
    }

    //set the seconds count
    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    //update the seconds label
    private void updateSecondsLabel(){
        this.getSecondsLabel().setText(Integer.toString(this.getSeconds()));
    }

    //init the seconds label
    private void initSecondsLabel(){
        this.secondsLabel = new JLabel("0");
        this.secondsLabel.setBorder(new EmptyBorder(0,0,0,25));

    }

    //get the seconds label
    public JLabel getSecondsLabel(){
        if(this.secondsLabel == null){
            initSecondsLabel();
        }
        return this.secondsLabel;
    }

    //init the mines remaining label
    private void initMinesRemainingLabel(){
        this.minesRemainingLabel = new JLabel("0");
        this.minesRemainingLabel.setBorder(new EmptyBorder(0,25,0,0));

    }

    //get the mines remaining label
    public JLabel getMinesRemainingLabel(){
        if(this.minesRemainingLabel == null){
            initMinesRemainingLabel();
        }
        return this.minesRemainingLabel;
    }

    //init the game status button
    private void initGameStatusButton(){
        ImageIcon defaultButtonImage = loadImage(SMILING_FACE_PATH);
        this.gameStatusButton = new JButton(defaultButtonImage);
        this.gameStatusButton.setMinimumSize(new Dimension(64,64));
        this.gameStatusButton.setMaximumSize(new Dimension(64,54));
        this.gameStatusButton.setPreferredSize(new Dimension(64,64));


    }

    //get the game status button
    public JButton getGameStatusButton(){
        if(this.gameStatusButton == null){
            initGameStatusButton();
        }
        return this.gameStatusButton;
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    public boolean isGameOver(){
        return this.gameOver;
    }
}
