package game.minesweeper;

import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    void newGridHasCorrectNumberOfRows() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        int numberOfRows = grid.getNumberOfRows();
        assertEquals(9, numberOfRows);
    }

    @Test
    void newGridHasCorrectNumberOfColumns() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        int numberOfColumns = grid.getNumberOfColumns();
        assertEquals(9, numberOfColumns);
    }

    @Test
    void girdCanGetCellWithInts() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        Cell cell = grid.getCell(6, 7);
        assertNotNull(cell);
    }

    @Test
    void girdCanGetCellWithCoordinate() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        Coordinate coordinate = new Coordinate(9,9);
        Cell cell = grid.getCell(coordinate);
        assertNotNull(cell);
    }

    @Test
    void newGridDoesNotHaveCellInIncorrectPosition() {
        GridOfSquares grid = new GridOfSquares(9, 9);
        Cell cell = grid.getCell(67, 67);
        assertNull(cell);
    }

    @Test
    void gridCanFindCorrectAmountOfNeighbors(){
        GridOfSquares grid = new GridOfSquares(9, 9);

        int angleNeighborCount = grid.getCellNeighbors(1,1).size();
        int boarderNeighborCount = grid.getCellNeighbors(6,1).size();
        int internalNeighborCount = grid.getCellNeighbors(6,7).size();

        assertEquals(3, angleNeighborCount);
        assertEquals(5, boarderNeighborCount);
        assertEquals(8, internalNeighborCount);
    }

    @Test
    void gridHasCorrectAmountOfCells(){
        GridOfSquares grid = new GridOfSquares(9,9);

        int coordinateCount = grid.getAllCoordinates().size();
        int cellsCount = grid.getAllCells().size();

        assertEquals(81, coordinateCount);
        assertEquals(81,cellsCount);
    }
}
