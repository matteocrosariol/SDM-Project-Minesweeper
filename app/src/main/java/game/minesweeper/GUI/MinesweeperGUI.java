package game.minesweeper.GUI;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.grid.GridOfSquares;
import game.minesweeper.engine.GridInitializer;

public class MinesweeperGUI {
    public static void main(String[] args) {

        int columns = 9;
        int rows = 9;
        int mines = 10;

        GridOfSquares grid = new GridOfSquares(rows, columns);
        new GridInitializer(grid).initialize(mines);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        new SwingUI().start(controller, grid, mines);

    }
}
