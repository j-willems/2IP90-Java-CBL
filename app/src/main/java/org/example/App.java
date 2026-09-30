package org.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(Color.red);
        bottomBar.setLayout(new BoxLayout(bottomBar, BoxLayout.X_AXIS));
        bottomBar.setBorder(new EmptyBorder(new Insets(0, 15, 0, 15)));
        bottomBar.setPreferredSize(new Dimension(0, 55));
        frame.add(bottomBar, BorderLayout.SOUTH);

        JButton playPauseButton = new JButton();
        playPauseButton.setText("play");
        bottomBar.add(playPauseButton);

        frame.pack();
        frame.setResizable(false);
        frame.setSize(1294, 700);
        frame.setVisible(true);
    }
}
