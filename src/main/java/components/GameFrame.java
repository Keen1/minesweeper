package components;

import controllers.GameController;
import handlers.difficulty.DifficultyHandler;
import models.GameModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/*
* game frame class
* contains all the panels and instantiates the game controller
*/
public class GameFrame extends JFrame {

    //static variables
    private static final Dimension BEGINNER_DIM = new Dimension(250,375);
    private static final Dimension INTERMEDIATE_DIM = new Dimension(425, 575);
    private static final Dimension EXPERT_DIM = new Dimension(775, 765);
    private static final int DEFAULT_BOARD_ROWS = 9;
    private static final int DEFAULT_BOARD_COLUMNS = 9;
    private static final int DEFAULT_MINE_COUNT = 10;
    private static final int INTERMEDIATE_BOARD_ROWS = 16;
    private static final int INTERMEDIATE_BOARD_COLUMNS = 16;
    private static final int INTERMEDIATE_MINE_COUNT = 40;
    private static final int EXPERT_BOARD_ROWS = 30;
    private static final int EXPERT_BOARD_COLUMNS = 24;
    private static final int EXPERT_BOARD_MINE_COUNT = 99;

    //class objects
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

    //constructor
    public GameFrame(){
        initGameController();
        initComponents();
        this.getGameController().getBoardPanel().setImagePaths(this.getGameModel());

    }

    //initialize the components of the frame
    private void initComponents(){

        initFrameAttributes();
        add(getScoreBoardPanel(), BorderLayout.NORTH);
        add(getBoardPanel(), BorderLayout.CENTER);
        setJMenuBar(getMenus());
        pack();
        setFrameIcon();

    }



    //initialize the game controller
    private void initGameController(){
        this.gameController = new GameController(this, this.getBoardPanel(), this.getScoreBoardPanel(), this.getGameModel());

    }

    //get the game controller
    public GameController getGameController(){

        if(this.gameController == null){

            initGameController();

        }

        return this.gameController;
    }

    //init the game model
    private void initGameModel(){
        this.gameModel = new GameModel();
    }

    //get the game model
    public GameModel getGameModel(){

        if(this.gameModel == null){

            initGameModel();

        }

        return gameModel;

    }

    //show the frame
    public void showFrame(){
        setVisible(true);
    }

    //initialize the frame's attributes
    private void initFrameAttributes(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(BEGINNER_DIM);
        setLayout(new BorderLayout());

    }

    //init the difficulty group
    private void initDifficultyGroup(){

        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(getBeginnerDifficultyMenuItem());
        difficultyGroup.add(getIntermediateDifficultyMenuItem());
        difficultyGroup.add(getExpertDifficultyMenuItem());
        this.getBeginnerDifficultyMenuItem().setSelected(true);

    }

    //initialize the expert difficulty menu item
    private void initExpertDifficultyMenuItem(){

        this.expertDifficultyMenuItem = new JCheckBoxMenuItem("expert");
        this.expertDifficultyMenuItem.addActionListener(new DifficultyHandler(this.getGameController(), EXPERT_DIM, EXPERT_BOARD_ROWS, EXPERT_BOARD_COLUMNS, EXPERT_BOARD_MINE_COUNT));

    }

    //get the expert difficulty menu item
    public JCheckBoxMenuItem getExpertDifficultyMenuItem(){

        if(this.expertDifficultyMenuItem == null){

            initExpertDifficultyMenuItem();

        }

        return this.expertDifficultyMenuItem;

    }

    //initialize the intermediate difficulty menu item
    private void initIntermediateDifficultyMenuItem(){

        this.intermediateDifficultyMenuItem = new JCheckBoxMenuItem("intermediate");
        this.intermediateDifficultyMenuItem.addActionListener(new DifficultyHandler(this.getGameController(), INTERMEDIATE_DIM, INTERMEDIATE_BOARD_ROWS, INTERMEDIATE_BOARD_COLUMNS, INTERMEDIATE_MINE_COUNT));

    }

    //get the intermediate difficulty menu item
    public JCheckBoxMenuItem getIntermediateDifficultyMenuItem(){

        if(this.intermediateDifficultyMenuItem == null){

            initIntermediateDifficultyMenuItem();

        }

        return this.intermediateDifficultyMenuItem;

    }

    //initialize the beginner difficulty menu item
    private void initBeginnerDifficultyMenuItem(){

        this.beginnerDifficultyMenuItem = new JCheckBoxMenuItem("beginner");
        this.beginnerDifficultyMenuItem.addActionListener(new DifficultyHandler(this.getGameController(), BEGINNER_DIM, DEFAULT_BOARD_ROWS, DEFAULT_BOARD_COLUMNS, DEFAULT_MINE_COUNT));

    }

    //get the beginner difficulty menu item
    public JCheckBoxMenuItem getBeginnerDifficultyMenuItem(){

        if(this.beginnerDifficultyMenuItem == null){

            initBeginnerDifficultyMenuItem();

        }

        return this.beginnerDifficultyMenuItem;

    }

    //initialize the difficulty menu
    private void initDifficultyMenu(){

        this.difficultySubMenu = new JMenu("difficulty");

        this.difficultySubMenu.add(getBeginnerDifficultyMenuItem());
        this.difficultySubMenu.add(getIntermediateDifficultyMenuItem());
        this.difficultySubMenu.add(getExpertDifficultyMenuItem());
        initDifficultyGroup();

    }

    //get the difficulty menu
    public JMenu getDifficultyMenu(){

        if(this.difficultySubMenu == null){

            initDifficultyMenu();

        }

        return this.difficultySubMenu;

    }

    //initialize the settings menu
    private void initSettingsMenu(){

        this.settingsMenu = new JMenu("settings");
        this.settingsMenu.add(getDifficultyMenu());

    }

    //get the settings menu
    public JMenu getSettingsMenu(){

        if(this.settingsMenu == null){

            initSettingsMenu();

        }

        return this.settingsMenu;

    }

    //initialize the menu bar
    private void initMenuBar(){

        this.menuBar = new JMenuBar();
        this.menuBar.add(getSettingsMenu());

    }

    //get the menus
    public JMenuBar getMenus(){

        if(this.menuBar == null){

            initMenuBar();

        }

        return this.menuBar;

    }

    private void setFrameIcon(){
        URL url = getClass().getResource("/logo/logo.png");
        Image logo = null;
        try{
            logo = ImageIO.read(Objects.requireNonNull(url));

        }catch(IOException e){
            System.out.printf("error setting program icon image: %s\n", e.getMessage());
        }

        if(logo != null){
            setIconImage(logo);
        }
    }

    //init the scoreboard panel
    public void initScoreBoardPanel(){

        this.scoreBoardPanel = new ScoreBoardPanel();

    }

    //get the scoreboard panel
    public ScoreBoardPanel getScoreBoardPanel(){

        if(this.scoreBoardPanel == null){

            initScoreBoardPanel();

        }

        return this.scoreBoardPanel;

    }

    //init the board panel
    public void initBoardPanel(){

        this.boardPanel = new BoardPanel();

    }

    //set the board panel
    public void setBoardPanel(BoardPanel boardPanel){

        this.boardPanel = boardPanel;

    }

    //get the board panel
    public BoardPanel getBoardPanel(){

        if(this.boardPanel == null){

            initBoardPanel();

        }

        return this.boardPanel;

    }

    //change the board panel
    public void changeBoardPanel(int rows, int cols, int mineCount, Dimension dimension){

        BoardPanel newBoardPanel = new BoardPanel(rows, cols, mineCount);
        BoardPanel currentBoardPanel = this.getBoardPanel();
        getContentPane().remove(currentBoardPanel);
        this.setBoardPanel(newBoardPanel);

        getContentPane().add(newBoardPanel, BorderLayout.CENTER);
        setPreferredSize(dimension);

        revalidate();
        repaint();
        pack();

        this.getGameController().setBoardPanel(newBoardPanel);
    }
}
