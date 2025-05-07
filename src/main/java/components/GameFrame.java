package components;

import controllers.GameController;
import models.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final Dimension FRAME_DIM = new Dimension(250, 350);
    private static final Dimension NINE_BY_NINE_DIM = new Dimension(250,300);
    private static final int DEFAULT_BOARD_ROWS = 9;
    private static final int DEFAULT_BOARD_COLUMNS = 9;
    private static final int DEFUALT_MINE_COUNT = 10;

    private  ScoreBoardPanel scoreBoardPanel;
    private  BoardPanel boardPanel;
    private GameController gameController;
    private GameModel gameModel;

    public GameFrame(){
        initComponents();
        initGameController();

    }


    private void initComponents(){

        initFrameAttributes();
        add(getScoreBoardPanel(), BorderLayout.NORTH);
        add(getBoardPanel(), BorderLayout.CENTER);
        pack();

    }

    private void initGameController(){
        this.gameController = new GameController(this, this.getBoardPanel(), this.getScoreBoardPanel(), this.getGameModel());
    }

    public GameController getGameController(){
        if(this.gameController == null){
            initGameController();
        }
        return this.gameController;
    }
    private void initGameModel(){
        this.gameModel = new GameModel();
    }
    public GameModel getGameModel(){
        if(this.gameModel == null){
            initGameModel();
        }
        return gameModel;
    }

    public void showFrame(){
        setVisible(true);
    }

    private void initFrameAttributes(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(FRAME_DIM);
        setLayout(new BorderLayout());
    }

    public void initScoreBoardPanel(){
        this.scoreBoardPanel = new ScoreBoardPanel();
    }
    public ScoreBoardPanel getScoreBoardPanel(){
        if(this.scoreBoardPanel == null){
            initScoreBoardPanel();
        }
        return this.scoreBoardPanel;
    }
    public void initBoardPanel(){
        this.boardPanel = new BoardPanel();
    }
    public BoardPanel getBoardPanel(){
        if(this.boardPanel == null){
            initBoardPanel();
        }
        return this.boardPanel;

    }
}
