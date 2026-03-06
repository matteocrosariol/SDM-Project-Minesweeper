package game.minesweeper;


import java.util.List;

public interface GridInterface<C extends CoordinateInterface> {

    Cell getCell(C coordinate);

    List<Cell> getCellNeighbors(C coordinate);

    List<Cell> getAllCells();

    List<C> getAllCoordinates();
}
