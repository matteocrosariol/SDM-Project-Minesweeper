package game.minesweeper;

import game.minesweeper.CLI.ConsoleUI;
import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

import java.util.Scanner;

import static game.minesweeper.CLI.StartingMenu.startMenu;

public class Minesweeper {


    public static void main(String[] args) {


        System.out.println("Welcome to Minesweeper!");

        Scanner scanner = new Scanner(System.in);
        String newGame;

        do {
            GridOfSquares grid = startMenu();

            Game<Coordinate> game = new Game<>(grid);
            GameController controller = new GameController(game);

            ConsoleUI ui = new ConsoleUI();
            ui.start(controller, grid);

            System.out.println("Do you want to start a new game?");
            newGame = scanner.next().toLowerCase();

        } while (newGame.equals("yes") || newGame.equals("y"));
    }

}
