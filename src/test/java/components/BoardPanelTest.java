package components;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardPanelTest {

    private static GameFrame gameFrame;

    private static final int BEGINNER_ROW_COL_COUNT = 9;
    private static final int BEGINNER_MINE_COUNT = 10;
    private static final int INTERMEDIATE_ROW_COL_COUNT = 16;
    private static final int INTERMEDIATE_MINE_COUNT = 40;
    private static final int EXPERT_ROW_COUNT = 30;
    private static final int EXPERT_COLUMN_COUNT = 24;
    private static final int EXPERT_MINE_COUNT = 99;



    @BeforeAll
    public static void setup(){
        gameFrame = new GameFrame();
    }

    @Test
    //test that selecting easy difficulty correctly sets the board rows
    public void testEasyDifficultyRowCount(){
        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        int rows = gameFrame.getGameController().getBoardPanelRows();
        assertEquals(BEGINNER_ROW_COL_COUNT, rows);
    }

    //test that selecting easy difficulty correctly sets the board columns
    @Test
    public void testBeginnerDifficultyColumnCount(){
        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        int columns = gameFrame.getGameController().getBoardPanelColumns();
        assertEquals(BEGINNER_ROW_COL_COUNT, columns);
    }

    //test that selecting easy difficulty correctly sets the mine count
    @Test
    public void testBeginnerDifficultyMineCount(){
        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        int mineCount = gameFrame.getGameController().getBoardPanelMineCount();
        assertEquals(BEGINNER_MINE_COUNT, mineCount);
    }

    //test that selecting intermediate difficulty correctly sets the board rows
    @Test
    public void testIntermediateDifficultyRowCount(){
        gameFrame.getIntermediateDifficultyMenuItem().doClick();
        int rows = gameFrame.getGameController().getBoardPanelRows();
        assertEquals(INTERMEDIATE_ROW_COL_COUNT, rows);
    }

    //test that selecting intermediate difficulty correctly sets the board columns
    @Test
    public void testIntermediateDifficultyColumnCount(){
        gameFrame.getIntermediateDifficultyMenuItem().doClick();

        int columns = gameFrame.getGameController().getBoardPanelColumns();
        assertEquals(INTERMEDIATE_ROW_COL_COUNT, columns);

    }

    //test that selecting intermediate difficulty correctly sets the board mine count
    @Test
    public void testIntermediateDifficultyMineCount(){
        gameFrame.getIntermediateDifficultyMenuItem().doClick();
        int mineCount = gameFrame.getGameController().getBoardPanelMineCount();
        assertEquals(INTERMEDIATE_MINE_COUNT, mineCount);
    }

    //test that selecting expert difficulty correctly sets the board rows
    @Test
    public void testExpertDifficultyRowCount(){
        gameFrame.getExpertDifficultyMenuItem().doClick();
        int rows = gameFrame.getGameController().getBoardPanelRows();
        assertEquals(EXPERT_ROW_COUNT, rows);
    }

    //test that selecting expert difficulty correctly sets the board columns
    @Test
    public void testExpertDifficultyColumnCount(){
        gameFrame.getExpertDifficultyMenuItem().doClick();
        int columns = gameFrame.getGameController().getBoardPanelColumns();
        assertEquals(EXPERT_COLUMN_COUNT, columns);
    }

    //test that selecting expert difficulty correctly sets the mine count
    @Test
    public void testExpertDifficultyMineCount(){
        gameFrame.getExpertDifficultyMenuItem().doClick();
        int mineCount = gameFrame.getGameController().getBoardPanelMineCount();
        assertEquals(EXPERT_MINE_COUNT, mineCount);
    }

    /*
    * test the user clicking a mine on the default board
    * TODO: anything that is testing the timer needs to be moved to a ScoreBoardPanelTest class
    */

    //test that the timer is stopped after the user clicks a mine on the default board
    @Test
    public void testMineClickTimerDisabledOnDefaultBoard(){
        resetFrame();

        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        clickMine();

        assertFalse(gameFrame.getScoreBoardPanel().getTimer().isRunning());

    }

    //test that the glass panel is enabled after the user clicks a mine on the default board
    @Test
    public void testMineClickGlassPanelEnabledOnDefaultBoard(){

        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        clickMine();

        assertTrue(gameFrame.getBoardPanel().getGlassPanel().isEnabled());


    }

    /*
    * test the user clicking a mine on the intermediate board
    */

    //test that the timer is stopped after the user clicks a mine on the intermediate board
    @Test
    public void testMineClickTimerDisabledOnIntermediateBoard(){
        resetFrame();
        gameFrame.getIntermediateDifficultyMenuItem().doClick();
        clickMine();
        assertFalse(gameFrame.getScoreBoardPanel().getTimer().isRunning());
    }

    //test that the glass panel is enabled after the user clicks a mine on the intermediate board
    @Test
    public void testMineClickGlassPanelEnabledOnIntermediateBoard(){
        gameFrame.getIntermediateDifficultyMenuItem().doClick();
        clickMine();
        assertTrue(gameFrame.getBoardPanel().getGlassPanel().isEnabled());
    }

    /*
    * test the user clicking a mine on the expert board
    * */

    //test that the timer is stopped after the user clicks a mine on the expert board
    @Test
    public void testMineClickTimerDisabledOnExpertBoard(){

        gameFrame.getExpertDifficultyMenuItem().doClick();
        clickMine();

        assertFalse(gameFrame.getScoreBoardPanel().getTimer().isRunning());
    }

    //test that the glass panel is enabled after the user clicks a mine on the expert board
    @Test
    public void testMineClickGlassPanelEnabledOnExpertBoard(){

        gameFrame.getExpertDifficultyMenuItem().doClick();
        clickMine();

        assertTrue(gameFrame.getBoardPanel().getGlassPanel().isEnabled());
    }

    /*
    * test setting flags on the gui sets them on the board
    */

    //test setting a flag on the default board
    @Test
    public void testUserFlagSetOnDefaultBoard(){

        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        int[] coords = setFlag();

        int row = coords[0];
        int col = coords[1];

        assertTrue(gameFrame.getBoardPanel().getCellButton(row, col).isUserFlagged());


    }

    //test removing a user flag on the default board
    @Test
    public void testRemovingUserFlagOnDefaultBoard(){

        gameFrame.getBeginnerDifficultyMenuItem().doClick();
        int[] coords = setFlag();
        int row = coords[0];
        int col = coords[1];
        removeFlag(row, col);

        assertFalse(gameFrame.getBoardPanel().getCellButton(row, col).isUserFlagged());
    }

    //test setting user flag on intermediate board
    @Test
    public void testUserFlagOnIntermediateBoard(){
        gameFrame.getIntermediateDifficultyMenuItem().doClick();
        int[] coords = setFlag();
        int row = coords[0];
        int col = coords[1];

        assertTrue(gameFrame.getBoardPanel().getCellButton(row, col).isUserFlagged());
    }

    //test removing a user flag on intermediate board
    @Test
    public void testRemovingUserFlagOnIntermediateBoard(){
        gameFrame.getIntermediateDifficultyMenuItem().doClick();
        int[] coords = setFlag();
        int row = coords[0];
        int col = coords[1];

        removeFlag(row, col);
        assertFalse(gameFrame.getBoardPanel().getCellButton(row, col).isUserFlagged());
    }

    //test setting user flag on expert board
    @Test
    public void testUserFlagOnExpertBoard(){
        gameFrame.getExpertDifficultyMenuItem().doClick();
        int[] coords = setFlag();
        int row = coords[0];
        int col = coords[1];

        assertTrue(gameFrame.getBoardPanel().getCellButton(row, col).isUserFlagged());
    }

    //test removing user flag on expert board
    @Test
    public void testRemovingUserFlagOnExpertBoard(){
        gameFrame.getExpertDifficultyMenuItem().doClick();
        int[] coords = setFlag();
        int row = coords[0];
        int col = coords[1];

        removeFlag(row, col);

        assertFalse(gameFrame.getBoardPanel().getCellButton(row, col).isUserFlagged());
    }

    //test that each cell that has adjacent mines has a non-null image path
    @Test
    public void testAdjacentMineImagePaths(){

        for(int i = 0; i < gameFrame.getBoardPanel().getCellButtons().length; i++){
            for(int j = 0; j < gameFrame.getBoardPanel().getCellButtons()[i].length; j++){

                if(gameFrame.getGameModel().getAdjacentMinesCount(i,j) > 0){

                    assertNotNull(gameFrame.getBoardPanel().getCellButton(i, j).getImagePathString());
                }
            }
        }
    }


    public void removeFlag(int row, int col){
        CellButton button = gameFrame.getBoardPanel().getCellButton(row, col);
        simulateRightClick(button);
    }

    public int[] setFlag(){
        CellButton button = getRandomCellButton();
        int[] coords = new int[2];
        coords[0] = button.getRow();
        coords[1] = button.getColumn();
        simulateRightClick(button);
        return coords;
    }

    public void simulateRightClick(CellButton button){

        int modifier = InputEvent.BUTTON3_DOWN_MASK;
        MouseEvent event = new MouseEvent(button, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), modifier,
                button.getWidth() / 2, button.getHeight() / 2, 1, false, MouseEvent.BUTTON3);

        button.dispatchEvent(event);
    }

    public CellButton getRandomCellButton(){

        int rows = gameFrame.getBoardPanel().getBoardRows();
        int columns = gameFrame.getBoardPanel().getBoardColumns();

        SecureRandom secureRand = new SecureRandom();
        int randomRow = secureRand.nextInt(rows);
        int randomColumn = secureRand.nextInt(columns);

        return gameFrame.getBoardPanel().getCellButton(randomRow, randomColumn);


    }

    public void resetFrame(){
        gameFrame = new GameFrame();
    }



    public void clickMine(){
        for(int i = 0; i < gameFrame.getGameModel().getBoard().getCells().length; i++){
            for(int j = 0; j < gameFrame.getGameModel().getBoard().getCells()[i].length; j++){
                if(gameFrame.getGameModel().getBoard().getCell(i,j).hasMine()){
                    gameFrame.getBoardPanel().getCellButton(i,j).doClick();
                    break;
                }
            }
        }
    }
}
