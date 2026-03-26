package game.minesweeper.engine;


import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridInitializer {

    private final GridOfSquares grid;
    private Set<Coordinate> minesCoordinate = new HashSet<>();

    public GridInitializer(GridOfSquares grid) {
        this.grid = grid;
    }

    public void initialize(int mineCount) {
        this.minesCoordinate = generateRandomSetOfCoordinates(mineCount);
        placeMines();
    }

    public void placeMine(Coordinate coordinate) {

        Cell cell = grid.getCell(coordinate);

        if (!cell.hasMine()) {

            cell.placeMine();
            minesCoordinate.add(coordinate);

            for (Cell neighborCell : grid.getCellNeighbors(coordinate)) {
                int neighborsMineCount = neighborCell.neighborsMineCount();
                neighborCell.setNeighborsMineCount(neighborsMineCount + 1);
            }
        }
    }

    public void placeMines() {
        for (Coordinate coordinate : minesCoordinate) {
            placeMine(coordinate);
        }
    }

    public GridOfSquares getGrid() {
        return grid;
    }

    public int getMineCount() {
        return minesCoordinate.size();
    }

    public Set<Coordinate> getMinesCoordinate() {
        return minesCoordinate;
    }

    public Set<Coordinate> generateRandomSetOfCoordinates(int mineCount) {

        Set<Coordinate> randomSetOfCoordinates = new HashSet<>();
        List<Coordinate> cellCoordinates = grid.getAllCoordinates();
        Collections.shuffle(cellCoordinates);

        for (int i = 0; i < mineCount; i++) {
            randomSetOfCoordinates.add(cellCoordinates.get(i));
        }

        return randomSetOfCoordinates;
    }

}
