package org.example.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.*;
import javax.swing.*;
import javax.swing.border.*;

public class PlayerView extends JPanel {

    JButton previousButton;
    JButton playPauseButton;
    JButton nextButton;
    JButton loopButton;

    Duration totalPlayTime;
    JProgressBar progressBar;

    JButton prependPlaylistButton;
    JButton insertPlaylistButton;
    JButton appendPlaylistButton;

    public PlayerView() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
                new EmptyBorder(new Insets(6, 15, 6, 15))
            )
        );

        this.previousButton = stylizedButton("󰒮");
        this.add(this.previousButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

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
        this.progressBar.setString("00:00/00:00");
        this.progressBar.setStringPainted(true);
        this.add(this.progressBar);
        this.add(Box.createHorizontalGlue());

        this.prependPlaylistButton = stylizedButton("󰑃");
        this.add(this.prependPlaylistButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.insertPlaylistButton = stylizedButton("");
        this.add(this.insertPlaylistButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.appendPlaylistButton = stylizedButton("󰑁");
        this.add(this.appendPlaylistButton);
    }

    public void setTotalPlayTime(Duration totalPlayTime) {
        this.progressBar.setMaximum((int) totalPlayTime.toMillis());
        this.totalPlayTime = totalPlayTime;

        int totalMinutes = totalPlayTime.toMinutesPart();
        int totalSeconds = totalPlayTime.toSecondsPart();

        this.progressBar.setString(
            String.format("00:00/%02d:%02d", totalMinutes, totalSeconds)
        );
    }

    public void updatePlayedTime(Duration playedTime) {
        this.progressBar.setValue((int) playedTime.toMillis());

        int minutesPlayed = playedTime.toMinutesPart();
        int secondsPlayed = playedTime.toSecondsPart();

        int totalMinutes = this.totalPlayTime.toMinutesPart();
        int totalSeconds = this.totalPlayTime.toSecondsPart();

        this.progressBar.setString(
            String.format(
                "%02d:%02d/%02d:%02d",
                minutesPlayed,
                secondsPlayed,
                totalMinutes,
                totalSeconds
            )
        );
    }

    public void changeButtonText(String buttonId, String text) {
        switch (buttonId) {
            case "playPauseButton":
                this.playPauseButton.setText(text);
                break;
            case "loopButton":
                this.loopButton.setText(text);
                break;
        }
    }

    public void addButtonListener(String buttonId, ActionListener listener) {
        switch (buttonId) {
            case "previousButton":
                this.previousButton.addActionListener(listener);
                break;
            case "playPauseButton":
                this.playPauseButton.addActionListener(listener);
                break;
            case "nextButton":
                this.nextButton.addActionListener(listener);
                break;
            case "loopButton":
                this.loopButton.addActionListener(listener);
                break;
            case "appendPlaylistButton":
                this.appendPlaylistButton.addActionListener(listener);
                break;
            case "insertPlaylistButton":
                this.insertPlaylistButton.addActionListener(listener);
                break;
            case "prependPlaylistButton":
                this.prependPlaylistButton.addActionListener(listener);
                break;
        }
    }

    public int askForInsertionIndex() {
        String input = (String) JOptionPane.showInputDialog(
            this,
            "Enter the index at which you'd like\nthe selected tracks to be inserted",
            "Enter insertion index",
            JOptionPane.PLAIN_MESSAGE
        );

        if (input != null && input.length() > 0) {
            try {
                int output = Integer.parseInt(input);

                if (output >= 0) {
                    return output;
                }
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        return 0;
    }

    private static JButton stylizedButton(String buttonText) {
        JButton button = new JButton(buttonText);
        Dimension size = new Dimension(44, 44);

        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setFocusPainted(false);

        button.setFont(UIManager.getFont("defaultFont").deriveFont(21.5f));

        return button;
    }
}
