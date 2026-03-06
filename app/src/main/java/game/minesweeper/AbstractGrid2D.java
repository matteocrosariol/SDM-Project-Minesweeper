package game.minesweeper;

import java.util.List;

public abstract class AbstractGrid2D extends AbstractGrid<Coordinate> {

    protected final int numberOfRows;
    protected final int numberOfColumns;

    public AbstractGrid2D(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        initCells();
    }

    @Override
    protected void initCells() {
        for (int row = 1; row <= numberOfRows; row++) {
            for (int column = 1; column <= numberOfColumns; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                cells.put(coordinate, new Cell());
            }
        }
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public Cell getCell(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);
        return cells.get(coordinate);
    }

    public List<Cell> getCellNeighbors(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);
        return getCellNeighbors(coordinate);
    }

}
