package game.minesweeper;

import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GridInitializerTest {

    @Test
    void properlyPlacedMine() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        GridInitializer gridInitializer = new GridInitializer(grid);
        Coordinate coordinate = new Coordinate(6, 7);
        Cell cell = grid.getCell(coordinate);

        gridInitializer.placeMine(coordinate);

        assertEquals(1, gridInitializer.getMineCount());
        assertTrue(cell.hasMine());
    }

    @Test
    void properlyPlacedMines() {

        GridOfSquares grid = new GridOfSquares(9, 9);
        GridInitializer gridInitializer = new GridInitializer(grid);
        int mineCount = 0;

        gridInitializer.initialize(10);
        Set<Coordinate> minesCoordinates = gridInitializer.getMinesCoordinate();

        for (Coordinate coordinate : grid.getAllCoordinates()) {
            if (minesCoordinates.contains(coordinate)) {
                assertTrue(grid.getCell(coordinate).hasMine());
                mineCount += 1;
            } else {
                assertFalse(grid.getCell(coordinate).hasMine());
            }
        }
        assertEquals(10, mineCount);
        assertEquals(10, gridInitializer.getMineCount());
    }


    @Test
    void correctNeighborMineCountInCell() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        GridInitializer initializer = new GridInitializer(grid);
        Coordinate c1 = new Coordinate(6, 6),
                c2 = new Coordinate(6, 7),
                c3 = new Coordinate(6, 8),
                c4 = new Coordinate(6, 5);

        initializer.placeMine(c1);
        initializer.placeMine(c3);
        int c2NeighborsMineCount = grid.getCell(c2).neighborsMineCount();
        int c4NeighborsMineCount = grid.getCell(c4).neighborsMineCount();

        assertEquals(2, c2NeighborsMineCount);
        assertEquals(1, c4NeighborsMineCount);

    }
}
