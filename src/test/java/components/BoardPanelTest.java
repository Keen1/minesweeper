package components;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
}
