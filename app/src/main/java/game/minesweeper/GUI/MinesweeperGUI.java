package game.minesweeper.GUI;

import game.minesweeper.Game;
import game.minesweeper.GameController;
import game.minesweeper.Grid;
import game.minesweeper.GridInitializer;

public class MinesweeperGUI {
    public static void main(String[] args) {

        int columns = 9;
        int rows = 9;
        int mines = 10;

        Grid grid = new Grid(rows, columns);
        new GridInitializer(grid).initialize(mines);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        new SwingUI().start(controller, grid, mines);

    }
}
