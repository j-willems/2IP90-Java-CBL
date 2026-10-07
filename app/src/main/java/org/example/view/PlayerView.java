package org.example.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;

public class PlayerView extends JPanel {

    JButton previousButton;
    JButton playPauseButton;
    JButton nextButton;
    JButton loopButton;

    JProgressBar progressBar;

    JButton prependPlaylistButton;
    JButton appendPlaylistButton;

    JButton stylizedButton(String buttonText) {
        JButton button = new JButton(buttonText);

        Dimension size = new Dimension(44, 44);

        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setBackground(Color.white);
        button.setFocusPainted(false);
        button.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black),
                new EmptyBorder(new Insets(10, 10, 10, 10))
            )
        );
        button.setFont(new Font("FiraCode Nerd Font Propo", Font.BOLD, 22));

        return button;
    }

    public PlayerView() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black),
                new EmptyBorder(new Insets(0, 15, 0, 15))
            )
        );
        this.setPreferredSize(new Dimension(0, 55));

        this.previousButton = stylizedButton("󰒮");
        this.add(this.previousButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

        // this.playPauseButton = stylizedButton("󰐎");
        this.playPauseButton = stylizedButton("󰐊");
        this.add(this.playPauseButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.nextButton = stylizedButton("󰒭");
        this.add(this.nextButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.loopButton = stylizedButton("󰑖");
        this.add(this.loopButton);

        this.add(Box.createRigidArea(new Dimension(25, 0)));

        this.progressBar = new JProgressBar();
        this.progressBar.setValue(35);
        this.add(this.progressBar);

        this.add(Box.createHorizontalGlue());

        this.prependPlaylistButton = stylizedButton("󰑃");
        this.add(this.prependPlaylistButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.appendPlaylistButton = stylizedButton("󰑁");
        this.add(this.appendPlaylistButton);
    }

    public void changePlayPauseButtonText(String text) {
        this.playPauseButton.setText(text);
    }

    public void addPlayPauseButtonListener(
        ActionListener playPauseButtonListener
    ) {
        this.playPauseButton.addActionListener(playPauseButtonListener);
    }

    public void changeLoopButtonText(String text) {
        this.loopButton.setText(text);
    }

    public void addLoopButtonListener(ActionListener loopButtonListener) {
        this.loopButton.addActionListener(loopButtonListener);
    }
}
