package game.minesweeper.engine;

import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

public class Game {

    private final GridOfSquares grid;
    private GameState gameState = GameState.RUNNING;

    public Game(GridOfSquares grid) {
        this.grid = grid;
    }

    public void openCell(Coordinate coordinate) {

        if (gameState != GameState.RUNNING) return;

        Cell cell = grid.getCell(coordinate);

        if (cell == null || cell.isRevealed() || cell.isFlagged()) return;

        if (cell.hasMine()) {
            gameState = GameState.LOST;
            cell.reveal();
            return;
        }

        revealRecursively(coordinate);

        checkWinCondition();
    }

    private void revealRecursively(Coordinate coordinate) {

        Cell cell = grid.getCell(coordinate);

        if (cell == null || cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        cell.reveal();

        if (cell.neighborsMineCount() == 0) {
            for (Coordinate neighbor : grid.getNeighborCoordinates(coordinate)) {
                revealRecursively(neighbor);
            }
        }
    }

    public void toggleFlag(Coordinate coordinate) {

        if (gameState != GameState.RUNNING) return;

        Cell cell = grid.getCell(coordinate);

        if (cell == null || cell.isRevealed()) return;

        cell.toggleFlag();
    }


    private void checkWinCondition() {
        for (Cell cell : grid.getAllCells()) {
            if (!cell.hasMine() && !cell.isRevealed()) {
                return;
            }
        }
        gameState = GameState.WON;
    }

    public GameState getState() {
        return gameState;
    }
}
