package game.minesweeper;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GameState;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    @Test
    void openCommandRevealsCell() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game game = new Game(grid);
        GameController controller = new GameController(game);

        controller.open(1,1);

        assertTrue(grid.getCell(new Coordinate(1,1)).isRevealed());
    }

    @Test
    void flagCommandFlagsCell() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game game = new Game(grid);
        GameController controller = new GameController(game);

        controller.toggleFlag(1,1);

        assertTrue(grid.getCell(new Coordinate(1,1)).isFlagged());
    }

    @Test
    void openMineThroughControllerEndsGame() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game game = new Game(grid);
        GameController controller = new GameController(game);
        GridInitializer initializer = new GridInitializer(grid);
        Coordinate mine = new Coordinate(1,1);

        initializer.placeMine(mine);
        controller.open(1,1);

        assertEquals(GameState.LOST, controller.getGameState());
    }

}
