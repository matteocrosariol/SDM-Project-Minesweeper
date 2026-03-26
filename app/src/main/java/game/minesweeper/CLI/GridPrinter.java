package game.minesweeper.CLI;

import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

public class GridPrinter {

    public static void print(GridOfSquares grid) {

        String string = "   ";

        for (int i = 1; i <= grid.getNumberOfColumns(); i++) {
            string += i + " ";
        }

        System.out.println(string);

        for (int row = 1; row <= grid.getNumberOfRows(); row++) {
            System.out.print(row + "  ");
            for (int col = 1; col <= grid.getNumberOfColumns(); col++) {

                Cell cell = grid.getCell(new Coordinate(row, col));

                if (!cell.isRevealed()) {

                    if (cell.isFlagged()) {
                        System.out.print("F ");
                    } else {
                        System.out.print("\u2580 ");
                    }

                } else if (cell.hasMine()) {

                    System.out.print("* ");

                } else {

                    System.out.print(cell.neighborsMineCount() + " ");
                }
            }

            System.out.println();
        }
    }
}