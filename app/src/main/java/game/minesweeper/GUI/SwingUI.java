package game.minesweeper.GUI;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GameState;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Cell;
import game.minesweeper.grid.GridOfSquares;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingUI {

    private GridOfSquares grid;
    private GameController controller;
    private JButton[][] buttons;
    private GameState gameState;
    private JFrame frame;
    private int mineCount;


    public void start(GameController controller, GridOfSquares grid, int mineCount) {
        this.grid = grid;
        this.controller = controller;
        this.gameState = controller.getGameState();
        this.mineCount = mineCount;
        SwingUtilities.invokeLater(this::buildUI);
    }

    private void buildUI() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gridPanel = buildGrid();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        frame.add(scrollPane);
        frame.pack();

        Rectangle usableScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        Dimension frameSize = frame.getSize();

        if (frameSize.width > usableScreen.width || frameSize.height > usableScreen.height) {
            frame.setResizable(true);
            int cappedWidth = Math.min(frameSize.width, usableScreen.width);
            int cappedHeight = Math.min(frameSize.height, usableScreen.height);
            frame.setSize(cappedWidth, cappedHeight);
        } else {
            frame.setResizable(false);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel buildGrid() {
        int rows = grid.getNumberOfRows();
        int columns = grid.getNumberOfColumns();
        buttons = new JButton[rows + 1][columns + 1];

        JPanel panel = new JPanel(new GridLayout(rows, columns));

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                JButton btn = createCellButton(i, j);
                buttons[i][j] = btn;
                panel.add(btn);
            }
        }

        return panel;
    }


    private JButton createCellButton(int row, int column) {
        JButton btn = new JButton("");

        btn.setPreferredSize(new Dimension(24, 24));

        // this is to "enforce" the text inside to appear, and to center it
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);

        btn.addMouseListener(new MouseAdapter() {

            // here there is something that might be handled in a different way,
            // maybe an error con be thrown by the game controller when cell is already flagged or open
            @Override
            public void mouseClicked(MouseEvent e) {

                if (gameState != GameState.RUNNING) return;

                if (SwingUtilities.isLeftMouseButton(e)) {

                    Cell cell = grid.getCell(row, column);

                    if (cell.isFlagged()) return;

                    controller.open(row, column);
                    refreshBoard();
                    checkEndgame();

                } else if (SwingUtilities.isRightMouseButton(e)) {

                    Cell cell = grid.getCell(row, column);

                    if (cell.isRevealed()) return;

                    controller.toggleFlag(row, column);
                    refreshBoard();

                }
            }
        });

        return btn;
    }


    private void checkEndgame() {

        gameState = controller.getGameState();

        if (gameState == GameState.RUNNING) return;

        if (gameState == GameState.WON) {
            showEndgameDialog("YOU WON!", "Congratulations, all mines cleared!");
        } else {
            showEndgameDialog("GAME OVER", "You hit a mine. Better luck next time!");
        }
    }


    private void showEndgameDialog(String title, String message) {
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setUndecorated(true);

        JPanel content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel msgLabel = new JLabel(message);
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgain = new JButton("Play Again");
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgain.addActionListener(e -> {
            dialog.dispose();
            frame.dispose();
            restartGame();
        });

        JButton quit = new JButton("Quit");
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.addActionListener(e -> System.exit(0));

        content.add(msgLabel);

        content.add(playAgain);

        content.add(quit);

        dialog.add(content, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    // this method might be too much dependent on Grid class
    // might not be compatible for different kind of grids

    private void refreshBoard() {

        int rows = grid.getNumberOfRows();
        int cols = grid.getNumberOfColumns();

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {

                Cell cell = grid.getCell(r, c);

                if (cell.isRevealed()) {
                    if (cell.hasMine()) {
                        buttons[r][c].setText("*");
                        buttons[r][c].setBackground(Color.RED);
                    } else {
                        buttons[r][c].setBackground(Color.GREEN);
                        int count = cell.neighborsMineCount();
                        if (count == 0) {
                            buttons[r][c].setText("");
                        } else {
                            buttons[r][c].setText(String.valueOf(count));
                        }
                    }
                } else {
                    if (cell.isFlagged()) buttons[r][c].setText("⚑");
                    if (!cell.isFlagged()) buttons[r][c].setText("");
                }
            }
        }


    }


    private void restartGame() {
        GridOfSquares newGrid = new GridOfSquares(grid.getNumberOfRows(), grid.getNumberOfColumns());
        new GridInitializer(newGrid).initialize(mineCount);
        Game newGame = new Game(newGrid);
        GameController newController = new GameController(newGame);
        start(newController, newGrid, mineCount);
    }
}
