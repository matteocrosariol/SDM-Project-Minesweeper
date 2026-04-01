package game.minesweeper.GUI;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.GridOfSquares;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartingMenuGUI {

    private JFrame frame;

    public void startMenu() {
        SwingUtilities.invokeLater(this::buildMenu);
    }

    public void buildMenu() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        frame.add(new JLabel("Welcome to Minesweeper"), c);

        c.gridy = 1;
        frame.add(new JLabel("Chose the difficulty mode"), c);

        c.gridy = 2;
        frame.add(difficultyButtonsPanel(), c);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel difficultyButtonsPanel() {
        JPanel difficultyButtonsPanel = new JPanel();

        difficultyButtonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;


        difficultyButtonsPanel.add(buildDifficultyButton("Easy", 9, 9, 10), c);
        c.gridy = 1;
        difficultyButtonsPanel.add(buildDifficultyButton("Medium", 16, 16, 40), c);
        c.gridy = 2;
        difficultyButtonsPanel.add(buildDifficultyButton("Hard", 16, 30, 99), c);
        c.gridy = 3;
        difficultyButtonsPanel.add(buildCustomizeDifficultyButton(), c);

        return difficultyButtonsPanel;

    }

    private JButton buildDifficultyButton(String difficultyLabelString, int rows, int columns, int mines) {
        JButton btn = new JButton();

        btn.setLayout(new BoxLayout(btn, BoxLayout.Y_AXIS));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGame(rows, columns, mines);
            }
        });

        JLabel difficultyLabel = new JLabel(difficultyLabelString);
        difficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.add(difficultyLabel);

        JLabel description = new JLabel(rows + " x " + columns + " grid, " + mines + " mines");
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.add(description);

        return btn;
    }

    private JButton buildCustomizeDifficultyButton() {
        JButton btn = new JButton("Customize");

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCustomizeDialog();
            }
        });

        return btn;
    }

    private void showCustomizeDialog() {
        JWindow customizeDialog = new JWindow(frame);

        JPanel content = new JPanel(new GridLayout(4,2));

        SpinnerNumberModel rowModel = new SpinnerNumberModel(9, 2, 99, 1);
        SpinnerNumberModel columnsModel = new SpinnerNumberModel(9, 2, 99, 1);
        SpinnerNumberModel mineModel = new SpinnerNumberModel(10, 1, 7000, 1);

        content.add(new JLabel("Rows:"));
        content.add(new JSpinner(rowModel));
        content.add(new JLabel("Columns:"));
        content.add(new JSpinner(columnsModel));
        content.add(new JLabel("Mines:"));
        content.add(new JSpinner(mineModel));

        JButton start = new JButton("Start");

        start.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int rows = rowModel.getNumber().intValue();
                int columns = columnsModel.getNumber().intValue();
                int mine = mineModel.getNumber().intValue();
                int maxMines = rows * columns - 1;

                if (mine > maxMines) {
                    JOptionPane.showMessageDialog(customizeDialog,
                            "Too many mines! Max for this grid: " + maxMines,
                            "Invalid configuration", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                startGame(rows, columns, mine);
            }

        });

        content.add(start);

        JButton close = new JButton("Close");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customizeDialog.dispose();
            }
        });

        content.add(close);

        customizeDialog.add(content);

        customizeDialog.pack();
        customizeDialog.setLocationRelativeTo(frame);
        customizeDialog.setVisible(true);

    }

    private void startGame(int rows, int columns, int mines) {

        frame.dispose();

        GridOfSquares grid = new GridOfSquares(rows, columns);
        new GridInitializer(grid).initialize(mines);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        new SwingUI().start(controller, grid, mines);

    }


}
