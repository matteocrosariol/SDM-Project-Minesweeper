package game.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void openingAMineEndsTheGame() {
        Grid grid = new Grid(2,2);
        Coordinate mine = new Coordinate(1,1);
        grid.getCell(mine).placeMine();

        Game game = new Game(grid);
        game.openCell(mine);
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    public void openingSafeCellRevealsIt(){
        Grid grid = new Grid(2,2);
        Coordinate safe = new Coordinate(1,1);

        Game game = new Game(grid);
        game.openCell(safe);

        assertTrue(grid.getCell(safe).isRevealed());
    }

    @Test
    public void openingSafeCellContinuesTheGame(){
        Grid grid = new Grid(2,2);
        Coordinate safe = new Coordinate(1,1);

        Game game = new Game(grid);
        game.openCell(safe);

        assertEquals(GameState.RUNNING, game.getState());
    }

    @Test
    public void cannotOpenCellAfterGameIsLost(){
        Grid grid = new Grid(2,2);
        Coordinate mine = new Coordinate(1,1);

        grid.getCell(mine).placeMine();

        Game game = new Game(grid);
        game.openCell(mine); //lose

        Coordinate other = new Coordinate(1,2);
        game.openCell(other);
        assertFalse(grid.getCell(other).isRevealed());
    }

    @Test
    void gameIsWonWhenAllSafeCellsAreRevealed() {
        Grid grid = new Grid(1, 2);

        Coordinate mine = new Coordinate(1,1);
        Coordinate safe = new Coordinate(1,2);

        grid.getCell(mine).placeMine();

        Game game = new Game(grid);

        game.openCell(safe);

        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void flaggedCellCannotBeOpened(){
        Grid grid = new Grid(2, 2);
        Coordinate cell = new Coordinate(1,1);

        Game game = new Game(grid);

        grid.getCell(cell).toggleFlag();
        game.openCell(cell);

        assertFalse(grid.getCell(cell).isRevealed());
    }
}
