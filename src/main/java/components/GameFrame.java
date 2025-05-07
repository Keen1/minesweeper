package components;

import controllers.GameController;
import models.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final Dimension FRAME_DIM = new Dimension(250, 375);
    private static final Dimension NINE_BY_NINE_DIM = new Dimension(250,375);
    private static final int DEFAULT_BOARD_ROWS = 9;
    private static final int DEFAULT_BOARD_COLUMNS = 9;
    private static final int DEFUALT_MINE_COUNT = 10;
    private static final int INTERMEDIATE_BOARD_ROWS = 16;
    private static final int INTERMEDIATE_BOARD_COLUMNS = 16;
    private static final int INTERMEDIATE_MINE_COUNT = 40;
    private static final int EXPERT_BOARD_ROWS = 30;
    private static final int EXPERT_BOARD_COLUMMNS = 16;
    private static final int EXPERT_BOARD_MINE_COUNT = 99;

    private  ScoreBoardPanel scoreBoardPanel;
    private  BoardPanel boardPanel;
    private GameController gameController;
    private GameModel gameModel;
    private JMenuBar menuBar;
    private JMenu settingsMenu;
    private JMenu difficultySubMenu;
    private JCheckBoxMenuItem beginnerDifficultyMenuItem;
    private JCheckBoxMenuItem intermediateDifficultyMenuItem;
    private JCheckBoxMenuItem expertDifficultyMenuItem;
    private ButtonGroup difficultyGroup;


    public GameFrame(){
        initComponents();
        initGameController();

    }


    private void initComponents(){

        initFrameAttributes();
        add(getScoreBoardPanel(), BorderLayout.NORTH);
        add(getBoardPanel(), BorderLayout.CENTER);
        setJMenuBar(getMenus());
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

    private void initDifficultyGroup(){
        this.difficultyGroup = new ButtonGroup();
        this.difficultyGroup.add(getBeginnerDifficultyMenuItem());
        this.difficultyGroup.add(getIntermediateDifficultyMenuItem());
        this.difficultyGroup.add(getExpertDifficultyMenuItem());
        this.getBeginnerDifficultyMenuItem().setSelected(true);
    }

    public ButtonGroup getDifficultyGroup(){
        if(this.difficultyGroup == null){
            initDifficultyGroup();
        }
        return this.difficultyGroup;
    }

    private void initExpertDifficultyMenuItem(){
        this.expertDifficultyMenuItem = new JCheckBoxMenuItem("expert");
    }
    public JCheckBoxMenuItem getExpertDifficultyMenuItem(){
        if(this.expertDifficultyMenuItem == null){
            initExpertDifficultyMenuItem();
        }
        return this.expertDifficultyMenuItem;
    }

    private void initIntermediateDifficultyMenuItem(){
        this.intermediateDifficultyMenuItem = new JCheckBoxMenuItem("intermediate");
    }
    public JCheckBoxMenuItem getIntermediateDifficultyMenuItem(){
        if(this.intermediateDifficultyMenuItem == null){
            initIntermediateDifficultyMenuItem();
        }
        return this.intermediateDifficultyMenuItem;
    }

    private void initBeginnerDifficultyMenuItem(){
        this.beginnerDifficultyMenuItem = new JCheckBoxMenuItem("beginner");

    }
    public JCheckBoxMenuItem getBeginnerDifficultyMenuItem(){
        if(this.beginnerDifficultyMenuItem == null){
            initBeginnerDifficultyMenuItem();
        }
        return this.beginnerDifficultyMenuItem;
    }
    private void initDifficultyMenu(){

        this.difficultySubMenu = new JMenu("difficulty");

        this.difficultySubMenu.add(getBeginnerDifficultyMenuItem());
        this.difficultySubMenu.add(getIntermediateDifficultyMenuItem());
        this.difficultySubMenu.add(getExpertDifficultyMenuItem());
        initDifficultyGroup();

    }

    public JMenu getDifficultyMenu(){
        if(this.difficultySubMenu == null){
            initDifficultyMenu();
        }
        return this.difficultySubMenu;
    }

    private void initSettingsMenu(){
        this.settingsMenu = new JMenu("settings");
        this.settingsMenu.add(getDifficultyMenu());
    }
    public JMenu getSettingsMenu(){
        if(this.settingsMenu == null){
            initSettingsMenu();
        }
        return this.settingsMenu;
    }

    private void initMenuBar(){
        this.menuBar = new JMenuBar();
        this.menuBar.add(getSettingsMenu());
    }
    public JMenuBar getMenus(){
        if(this.menuBar == null){
            initMenuBar();
        }
        return this.menuBar;
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
