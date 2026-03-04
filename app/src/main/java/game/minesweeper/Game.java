package game.minesweeper;

public class Game {

    private final Grid grid;
    private GameState gameState = GameState.RUNNING;

    public Game(Grid grid) {
        this.grid = grid;
    }

    public void openCell(Coordinate coordinate) {
        if (gameState != GameState.RUNNING) {
            return;
        }

        Cell cell = grid.getCell(coordinate);

        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        if (cell.hasMine()) {
            gameState = GameState.LOST;
            return;
        }

        cell.reveal();
        checkWinCondition();

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
