package models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameModelTest {
    private static GameModel gameModel;

    private static final int BEGINNER_ROWS_COLUMNS = 9;
    private static final int BEGINNER_MINES = 10;
    private static final int INTERMEDIATE_ROWS_COLUMNS = 16;
    private static final int INTERMEDIATE_MINES = 40;
    private static final int EXPERT_ROWS = 30;
    private static final int EXPERT_COLUMNS = 24;
    private static final int EXPERT_MINES = 99;



    @BeforeAll
    public static void setup(){
        gameModel = new GameModel();
    }

    @Test
    public void testDefaultRows(){
        setDefaultBoard();
        int rows = gameModel.getBoardModelRows();
        assertEquals(BEGINNER_ROWS_COLUMNS, rows);

    }

    @Test
    public void testDefaultColumns(){
        setDefaultBoard();
        int columns = gameModel.getBoardModelColumns();
        assertEquals(BEGINNER_ROWS_COLUMNS, columns);
    }

    @Test
    public void testDefaultMineCount(){
        setDefaultBoard();
        int mineCount = gameModel.getMineCount();
        assertEquals(BEGINNER_MINES, mineCount);
    }

    @Test
    public void testIntermediateRows(){
        setIntermediateBoard();
        int rows = gameModel.getBoardModelRows();
        assertEquals(INTERMEDIATE_ROWS_COLUMNS, rows);
    }

    @Test
    public void testIntermediateColumns(){
        setIntermediateBoard();
        int columns = gameModel.getBoardModelColumns();
        assertEquals(INTERMEDIATE_ROWS_COLUMNS, columns);
    }

    @Test
    public void testIntermediateMineCount(){
        setIntermediateBoard();
        int mineCount = gameModel.getMineCount();
        assertEquals(INTERMEDIATE_MINES, mineCount);
    }

    @Test
    public void testExpertRows(){
        setExpertBoard();
        int rows = gameModel.getBoardModelRows();
        assertEquals(EXPERT_ROWS, rows);
    }

    @Test
    public void testExpertColumns(){
        setExpertBoard();
        int columns = gameModel.getBoardModelColumns();
        assertEquals(EXPERT_COLUMNS, columns);
    }

    @Test
    public void testExpertMineCount(){
        setExpertBoard();
        int mineCount = gameModel.getMineCount();
        assertEquals(EXPERT_MINES, mineCount);
    }


    public void setDefaultBoard(){
        gameModel.resetBoard(BEGINNER_ROWS_COLUMNS, BEGINNER_ROWS_COLUMNS, BEGINNER_MINES);
    }

    public void setIntermediateBoard(){
        gameModel.resetBoard(INTERMEDIATE_ROWS_COLUMNS, INTERMEDIATE_ROWS_COLUMNS, INTERMEDIATE_MINES);
    }

    public void setExpertBoard(){
        gameModel.resetBoard(EXPERT_ROWS, EXPERT_COLUMNS, EXPERT_MINES);
    }
}
