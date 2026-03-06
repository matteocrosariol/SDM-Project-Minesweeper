package game.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Grid extends AbstractGrid2D {

    public Grid(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);
    }


    @Override
    public List<Cell> getCellNeighbors(Coordinate coordinate) {

        int row = coordinate.row();
        int column = coordinate.column();

        List<Cell> neighbors = new ArrayList<>();

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                Coordinate neighbor = new Coordinate(row + dr, column + dc);
                Cell cell = cells.get(neighbor);
                if (cell != null) neighbors.add(cell);
            }
        }
        return neighbors;
    }

}
