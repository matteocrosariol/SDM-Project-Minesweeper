package game.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void openingAMineEndsTheGame() {
        Grid grid = new Grid(2,2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);
        Coordinate mine = new Coordinate(1,1);

        initializer.placeMine(mine);
        game.openCell(mine);

        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    public void openingSafeCellRevealsIt(){
        Grid grid = new Grid(2,2);
        Game game = new Game(grid);
        Coordinate safe = new Coordinate(1,1);


        game.openCell(safe);

        assertTrue(grid.getCell(safe).isRevealed());
    }

    @Test
    public void openingSafeCellContinuesTheGame(){
        Grid grid = new Grid(2,2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);

        Coordinate mine = new Coordinate(1,1);
        Coordinate safe = new Coordinate(2,2);

        initializer.placeMine(mine);


        game.openCell(safe);

        assertEquals(GameState.RUNNING, game.getState());
    }

    @Test
    public void cannotOpenCellAfterGameIsLost(){
        Grid grid = new Grid(2,2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);
        Coordinate mine = new Coordinate(1,1);

        initializer.placeMine(mine);


        game.openCell(mine); //lose

        Coordinate other = new Coordinate(1,2);
        game.openCell(other);
        assertFalse(grid.getCell(other).isRevealed());
    }

    @Test
    void gameIsWonWhenAllSafeCellsAreRevealed() {
        Grid grid = new Grid(1, 2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);

        Coordinate mine = new Coordinate(1,1);
        Coordinate safe = new Coordinate(1,2);

        initializer.placeMine(mine);



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

    @Test
    void openingCellWithZeroNeighborMinesRevealsNeighbors() {
        Grid grid = new Grid(3,3);

        Game game = new Game(grid);

        Coordinate center = new Coordinate(2,2);

        game.openCell(center);

        for(Cell neighbor : grid.getCellNeighbors(center)) {
            assertTrue(neighbor.isRevealed());
        }
    }

}
