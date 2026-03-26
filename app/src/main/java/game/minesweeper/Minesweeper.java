package game.minesweeper;

import game.minesweeper.CLI.ConsoleUI;
import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.GridOfSquares;

public class Minesweeper {


    public static void main(String[] args) {

        GridOfSquares grid = new GridOfSquares(8, 8);

        GridInitializer initializer = new GridInitializer(grid);
        initializer.initialize(10);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        ConsoleUI ui = new ConsoleUI();
        ui.start(controller, grid);
    }

}
