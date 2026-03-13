package game.minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGrid<C extends CoordinateInterface> implements GridInterface<C> {

    protected final Map<C, Cell> cells;

    protected AbstractGrid() {
        this.cells = new HashMap<>();
    }

    protected abstract void initCells();

    @Override
    public Cell getCell(C coordinate) {
        return cells.get(coordinate);
    }

    @Override
    public List<Cell> getAllCells() {
        return new ArrayList<>(cells.values());
    }

    @Override
    public List<Cell> getCellNeighbors(C coordinate) {

        List<Cell> neighbors = new ArrayList<>();

        for (C neighbor : getNeighborCoordinates(coordinate)) {
            Cell cell = getCell(neighbor);
            neighbors.add(cell);
        }

        return neighbors;
    }

    @Override
    public List<C> getAllCoordinates() {
        return new ArrayList<>(cells.keySet());
    }
}