package game.minesweeper.CLI;

import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GameState;
import game.minesweeper.grid.GridOfSquares;

import java.util.Scanner;

public class ConsoleUI {

    public void start(GameController controller, GridOfSquares grid) {

        Scanner scanner = new Scanner(System.in);

        while (controller.getGameState() == GameState.RUNNING) {

            GridPrinter.print(grid);

            System.out.println("Enter command: open row col | flag row col");

            String command = scanner.next();
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (command.equals("open")) {
                controller.open(row, col);
            }

            if (command.equals("flag")) {
                controller.toggleFlag(row, col);
            }
        }

        GridPrinter.print(grid);

        if (controller.getGameState() == GameState.WON) {
            System.out.println("You won!");
        } else {
            System.out.println("Game over!");
        }
    }
}