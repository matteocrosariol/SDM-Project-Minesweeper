package game.minesweeper.GUI;

import javax.swing.*;
import java.awt.*;

public class FooterGUI extends JPanel {

    private boolean rulesShown =  false;
    private boolean commandsShown =  false;

    public FooterGUI() {

        setLayout(new BorderLayout());

        JButton rulesButton = new JButton("Rules");
        rulesButton.addActionListener(e -> showRules());
        add(rulesButton, BorderLayout.EAST);

        JButton commandsButton = new JButton("Commands");
        commandsButton.addActionListener(e -> showCommands());
        add(commandsButton, BorderLayout.WEST);

        setVisible(true);

    }

    private void showRules() {

        if (rulesShown) return;
        else rulesShown = true;

        JWindow rules = new JWindow();
        rules.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 12, 4, 12);

        JLabel rulesLabel = new JLabel("Rules");
        rulesLabel.setFont(rulesLabel.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridy = 0;
        rules.add(rulesLabel, gbc);

        JLabel cellsLabel = new JLabel("Cells can be either hidden or open. You can open one by left-clicking on it.\n\r" +
                "A closed cell can hide a mine; opening a cell with a mine will make you lose.\n\r" +
                "Otherwise a number is shown, indicating how many mines are adjacent to that cell.");
        gbc.gridy = 1;
        rules.add(cellsLabel, gbc);

        JLabel flagsLabel = new JLabel("You can place or remove a flag by right-clicking on a cell.\n\r" +
                "A cell with a flag on it cannot be opened.");
        gbc.gridy = 2;
        rules.add(flagsLabel, gbc);

        JButton closeButton = new JButton("Close");
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        closeButton.addActionListener(e -> rules.dispose());

        rules.add(closeButton, gbc);

        rules.pack();
        rules.setLocationRelativeTo(null);
        rules.setVisible(true);
    }

    private void showCommands() {

        if  (commandsShown) return;
        else commandsShown = true;

        JWindow commands = new JWindow();
        commands.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 12, 4, 12);

        JLabel commandsLabel = new JLabel("Commands");
        commandsLabel.setFont(commandsLabel.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridy = 0;
        commands.add(commandsLabel, gbc);

        JLabel leftClick = new JLabel("Left-click on a hidden cell to open it, unless it has a flag.");
        gbc.gridy = 1;
        commands.add(leftClick, gbc);

        JLabel rightClick = new JLabel("Right-click on a hidden cell to place or remove a flag.");
        gbc.gridy = 2;
        commands.add(rightClick, gbc);

        JButton closeButton = new JButton("Close");
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        closeButton.addActionListener(e -> commands.dispose());
        commands.add(closeButton, gbc);

        commands.pack();
        commands.setLocationRelativeTo(null);
        commands.setVisible(true);
    }
}
