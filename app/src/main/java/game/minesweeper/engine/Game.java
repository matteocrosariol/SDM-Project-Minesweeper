package game.minesweeper.engine;

import game.minesweeper.grid.*;

public class Game<C extends CoordinateInterface> {

    private final AbstractGrid<C> grid;
    private GameState gameState = GameState.RUNNING;

    public Game(AbstractGrid<C> grid) {
        this.grid = grid;
    }

    public void openCell(C coordinate) {

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

    private void revealRecursively(C coordinate) {

        Cell cell = grid.getCell(coordinate);

        if (cell == null || cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        cell.reveal();

        if (cell.neighborsMineCount() == 0) {
            for (C neighbor : grid.getNeighborCoordinates(coordinate)) {
                revealRecursively(neighbor);
            }
        }
    }

    public void toggleFlag(C coordinate) {

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
