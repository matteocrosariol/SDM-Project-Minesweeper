package game.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Grid extends AbstractGrid2D {

    public Grid(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);
    }


    @Override
    public List<Coordinate> getNeighborCoordinates(Coordinate coordinate) {

        int row = coordinate.row();
        int column = coordinate.column();

        List<Coordinate> neighbors = new ArrayList<>();

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {

                if (dr == 0 && dc == 0) continue;

                Coordinate neighbor = new Coordinate(row + dr, column + dc);

                if (cells.containsKey(neighbor)) {
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }

}
