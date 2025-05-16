package models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {
    private static GameModel gameModel;

    private static final int BEGINNER_ROWS_COLUMNS = 9;
    private static final int BEGINNER_MINES = 10;
    private static final int INTERMEDIATE_ROWS_COLUMNS = 16;
    private static final int INTERMEDIATE_MINES = 40;
    private static final int EXPERT_ROWS = 30;
    private static final int EXPERT_COLUMNS = 24;
    private static final int EXPERT_MINES = 99;


    //instantiate the game model before running any tests
    @BeforeAll
    public static void setup(){
        gameModel = new GameModel();
    }
    //test the default row count
    @Test
    public void testDefaultRows(){
        setDefaultBoard();
        int rows = gameModel.getBoardModelRows();
        assertEquals(BEGINNER_ROWS_COLUMNS, rows);

    }

    //test the default column count
    @Test
    public void testDefaultColumns(){
        setDefaultBoard();
        int columns = gameModel.getBoardModelColumns();
        assertEquals(BEGINNER_ROWS_COLUMNS, columns);
    }

    //test the default mine count
    @Test
    public void testDefaultMineCount(){
        setDefaultBoard();
        int mineCount = gameModel.getMineCount();
        assertEquals(BEGINNER_MINES, mineCount);
    }

    //test the intermediate row count
    @Test
    public void testIntermediateRows(){
        setIntermediateBoard();
        int rows = gameModel.getBoardModelRows();
        assertEquals(INTERMEDIATE_ROWS_COLUMNS, rows);
    }

    //test the intermediate column count
    @Test
    public void testIntermediateColumns(){
        setIntermediateBoard();
        int columns = gameModel.getBoardModelColumns();
        assertEquals(INTERMEDIATE_ROWS_COLUMNS, columns);
    }

    //test the intermediate mine count
    @Test
    public void testIntermediateMineCount(){
        setIntermediateBoard();
        int mineCount = gameModel.getMineCount();
        assertEquals(INTERMEDIATE_MINES, mineCount);
    }

    //test the expert row count
    @Test
    public void testExpertRows(){
        setExpertBoard();
        int rows = gameModel.getBoardModelRows();
        assertEquals(EXPERT_ROWS, rows);
    }

    //test the expert column count
    @Test
    public void testExpertColumns(){
        setExpertBoard();
        int columns = gameModel.getBoardModelColumns();
        assertEquals(EXPERT_COLUMNS, columns);
    }

    //test the expert mine count
    @Test
    public void testExpertMineCount(){
        setExpertBoard();
        int mineCount = gameModel.getMineCount();
        assertEquals(EXPERT_MINES, mineCount);
    }
    //test the default win state with flagged mines count
    @Test
    public void testDefaultWinStateFlaggedMinesCount(){
        setDefaultBoard();
        setWinState();
        int flaggedMines = gameModel.getMinesFlaggedCount();
        assertEquals(BEGINNER_MINES, flaggedMines);

    }

    //test the intermediate win state with flagged mines count
    @Test
    public void testIntermediateWinStateFlaggedMinesCount(){
        setIntermediateBoard();
        setWinState();
        int flaggedMines = gameModel.getMinesFlaggedCount();
        assertEquals(INTERMEDIATE_MINES, flaggedMines);
    }

    //test the default false win state with the flagged mines count
    @Test
    public void testDefaultFalseWinStateFlaggedMinesCount(){
        setDefaultBoard();
        setFalseWinState();
        int flaggedMines = gameModel.getMinesFlaggedCount();
        assertEquals(BEGINNER_MINES - 1, flaggedMines);
    }

    //test the intermediate false win state with flagged mines count
    @Test
    public void testIntermediateFalseWinStateFlaggedMinesCount(){
        setIntermediateBoard();
        setFalseWinState();
        int flaggedMines = gameModel.getMinesFlaggedCount();
        assertEquals(INTERMEDIATE_MINES - 1, flaggedMines);
    }

    //test default false win state with flagged count
    @Test
    public void testDefaultFalseWinStateFlagCount(){
        setDefaultBoard();
        setFalseWinState();
        int flags = gameModel.getFlaggedCount();
        assertEquals(BEGINNER_MINES, flags);
    }

    //test the intermediate false win state with flagged count
    @Test
    public void testIntermediateWinStateFlagCount(){
        setIntermediateBoard();
        setFalseWinState();
        int flags = gameModel.getFlaggedCount();
        assertEquals(INTERMEDIATE_MINES, flags);
    }

    //test the default win state with the boolean flag
    @Test
    public void testDefaultWinStateBooleanFlag(){
        setDefaultBoard();
        setWinState();
        boolean won = gameModel.isGameWon();
        assertTrue(won);
    }

    //test the intermediate win state with the boolean flag
    @Test
    public void tesIntermediateWinStateBooleanFlag(){
        setIntermediateBoard();
        setWinState();
        boolean won = gameModel.isGameWon();
        assertTrue(won);
    }

    //test the default false win state with the boolean flag
    @Test
    public void testDefaultFalseWinStateBooleanFlag(){
        setDefaultBoard();
        setFalseWinState();
        boolean won = gameModel.isGameWon();
        assertFalse(won);
    }

    @Test
    public void testIntermediateWinStateBooleanFlag(){
        setIntermediateBoard();
        setFalseWinState();
        boolean won = gameModel.isGameWon();
        assertFalse(won);
    }




    public void setWinState(){
        for(int i = 0; i < gameModel.getBoardModelRows(); i++){
            for(int j = 0; j < gameModel.getBoardModelColumns(); j++){
                if(gameModel.getBoard().getCell(i,j).hasMine()){
                    gameModel.getBoard().getCell(i,j).setUserFlag(true);
                }
            }
        }
        updateModel();
    }

    public void setFalseWinState(){
        int minesFound = 0;
        int falseFlag = 0;

        for(int i = 0; i < gameModel.getBoardModelRows(); i++){

            if(minesFound == gameModel.getMineCount() - 1)
                break;

            for(int j = 0; j < gameModel.getBoardModelColumns(); j++){

                if(minesFound == gameModel.getMineCount() - 1)
                    break;

                if(gameModel.getBoard().getCell(i, j).hasMine()){
                    gameModel.getBoard().getCell(i,j).setUserFlag(true);
                    minesFound++;
                }

                //set a false flag to test a false win state
                if(!gameModel.getBoard().getCell(i,j).hasMine() && falseFlag < 1){
                    gameModel.getBoard().getCell(i,j).setUserFlag(true);
                    falseFlag++;
                }

            }
        }
        updateModel();
    }

    public void updateModel(){
        gameModel.updateFlaggedCount();
        gameModel.updateMinesFlaggedCount();
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
