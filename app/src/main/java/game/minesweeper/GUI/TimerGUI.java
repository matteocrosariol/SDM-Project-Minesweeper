package game.minesweeper.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerGUI extends JLabel {
    private int seconds = 0;
    private Timer timer;

    public TimerGUI() {
        setText("0000");
        setOpaque(true);
        setBackground(new Color(0, 0, 0));
        setForeground(new Color(0, 150, 50));
        setHorizontalAlignment(SwingConstants.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                setText(String.format("%04d", seconds));
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        seconds = 0;
        setText("000");
    }

    public int getSeconds() {
        return seconds;
    }
}