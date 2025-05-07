package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class ScoreBoardPanel extends JPanel{
    private static final String DEAD_FACE_PATH = "/images/dead_face.png";
    private static final String SMILING_FACE_PATH = "/images/smiling_face.png";
    private static final String SUNGLASSES_FACE_PATH = "/images/sunglasses_face.png";
    private static final String SURPRISED_FACE_PATH = "/images/surprised_face.png";
    private JLabel minesRemainingLabel;
    private JLabel secondsLabel;
    private JButton gameStatusButton;

    private Timer timer;

    int seconds = 0;

    public ScoreBoardPanel(){
        initComponents();
    }

    private void initComponents(){

        setLayout(new BorderLayout());
        add(getSecondsLabel(), BorderLayout.EAST);
        JPanel buttonWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapperPanel.add(getGameStatusButton());
        add(buttonWrapperPanel, BorderLayout.CENTER);
        add(getMinesRemainingLabel(), BorderLayout.WEST);

        setVisible(true);

    }

    public ImageIcon loadImage(String path){
        URL imgURL = getClass().getResource(path);
        return new ImageIcon(Objects.requireNonNull(imgURL));

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
        this.secondsLabel.setBorder(new EmptyBorder(0,0,0,25));

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
        this.minesRemainingLabel.setBorder(new EmptyBorder(0,25,0,0));

    }
    public JLabel getMinesRemainingLabel(){
        if(this.minesRemainingLabel == null){
            initMinesRemainingLabel();
        }
        return this.minesRemainingLabel;
    }

    private void initGameStatusButton(){
        ImageIcon defaultButtonImage = loadImage(SMILING_FACE_PATH);
        this.gameStatusButton = new JButton(defaultButtonImage);
        this.gameStatusButton.setMinimumSize(new Dimension(64,64));
        this.gameStatusButton.setMaximumSize(new Dimension(64,54));
        this.gameStatusButton.setPreferredSize(new Dimension(64,64));
    }
    public JButton getGameStatusButton(){
        if(this.gameStatusButton == null){
            initGameStatusButton();
        }
        return this.gameStatusButton;
    }


}
