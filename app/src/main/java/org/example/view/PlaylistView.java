package org.example.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import org.example.shared.Track;

public class PlaylistView extends JPanel {

    JPanel playlistBar;
    JLabel playlistLabel;
    JFileChooser fileChooser;
    JButton saveFileButton;
    JButton loadFileButton;

    JPanel headerBar;

    JList tracksList;
    JScrollPane scrollPane;

    public PlaylistView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(0, 15, 0, 15)));

        this.playlistBar = new JPanel();
        this.playlistBar.setLayout(
            new BoxLayout(this.playlistBar, BoxLayout.X_AXIS)
        );
        this.playlistBar.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(new Insets(6, 0, 6, 0))
            )
        );

        this.playlistLabel = new JLabel("Playlist: unsaved playlist");
        this.playlistBar.add(this.playlistLabel);
        this.playlistBar.add(Box.createHorizontalGlue());

        this.saveFileButton = stylizedButton("");
        this.playlistBar.add(this.saveFileButton);

        this.loadFileButton = stylizedButton("");
        this.playlistBar.add(this.loadFileButton);

        this.fileChooser = new JFileChooser(
            FileSystemView.getFileSystemView().getHomeDirectory()
        );
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.fileChooser.setFileFilter(
            new FileNameExtensionFilter("Playlist file (*.pll)", "pll")
        );
        this.fileChooser.setAcceptAllFileFilterUsed(false);

        this.headerBar = new JPanel();
        this.headerBar.setLayout(
            new BoxLayout(this.headerBar, BoxLayout.X_AXIS)
        );
        this.headerBar.setBorder(new EmptyBorder(new Insets(6, 8, 1, 0)));

        this.headerBar.add(new JLabel(resizeString("#", 4)));
        this.headerBar.add(Box.createRigidArea(new Dimension(15, 0)));

        // this.headerBar.add(new JLabel(resizeString("Cover", 20)));
        // this.headerBar.add(Box.createRigidArea(new Dimension(20, 0)));

        this.headerBar.add(new JLabel(resizeString("Title", 35)));
        this.headerBar.add(Box.createRigidArea(new Dimension(15, 0)));

        this.headerBar.add(new JLabel(resizeString("Artist", 35)));
        this.headerBar.add(Box.createRigidArea(new Dimension(15, 0)));

        this.headerBar.add(new JLabel(resizeString("Album", 35)));
        this.headerBar.add(Box.createRigidArea(new Dimension(15, 0)));

        this.headerBar.add(new JLabel(resizeString("Duration", 8)));
        this.headerBar.add(Box.createHorizontalGlue());

        this.tracksList = new JList<Track>();
        this.tracksList.setCellRenderer(new TrackRenderer());
        this.tracksList.setFocusable(false);

        this.scrollPane = new JScrollPane(
            this.tracksList,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        this.add(this.playlistBar);
        this.add(this.headerBar);
        this.add(this.scrollPane);
    }

    public List<Track> getSelectedTracks() {
        return this.tracksList.getSelectedValuesList();
    }

    public String selectFile(boolean isLoadOperation) {
        this.fileChooser.showDialog(
            this,
            isLoadOperation ? "Open playlist" : "Save playlist"
        );
        return this.fileChooser.getSelectedFile().getAbsolutePath();
    }

    public void setPlaylistContent(ListModel<Track> model) {
        this.tracksList.setModel(model);
    }

    public void setPlaylistLabelText(String labelText) {
        this.playlistLabel.setText("Playlist: " + labelText);
    }

    public void addFileChooserButtonListener(
        ActionListener fileChooserButtonListener,
        String buttonId
    ) {
        switch (buttonId) {
            case "loadFileButton":
                this.loadFileButton.addActionListener(
                    fileChooserButtonListener
                );
                break;
            case "saveFileButton":
                this.saveFileButton.addActionListener(
                    fileChooserButtonListener
                );
                break;
        }
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

    private static String resizeString(String input, int finalSize) {
        if (input == null) {
            return String.format("%-" + finalSize + "s", "");
        }

        if (input.length() > finalSize) {
            return input.substring(0, finalSize - 3) + "...";
        }

        return String.format("%-" + finalSize + "s", input);
    }

    class TrackRenderer extends JPanel implements ListCellRenderer<Track> {

        JPanel trackCell;

        public TrackRenderer() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        public Component getListCellRendererComponent(
            JList<? extends Track> list,
            Track value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            this.trackCell = new JPanel();
            this.trackCell.setLayout(
                new BoxLayout(this.trackCell, BoxLayout.X_AXIS)
            );
            this.trackCell.setBorder(new EmptyBorder(new Insets(3, 4, 3, 0)));
            this.trackCell.setBackground(
                isSelected ? Color.LIGHT_GRAY : this.getBackground()
            );

            this.trackCell.add(
                new JLabel(resizeString(String.format("%03d", index + 1), 4))
            );
            this.trackCell.add(Box.createRigidArea(new Dimension(15, 0)));

            // this.trackCell.add(new JLabel(new ImageIcon()));
            // this.trackCell.add(Box.createRigidArea(new Dimension(15, 0)));

            this.trackCell.add(new JLabel(resizeString(value.getTitle(), 35)));
            this.trackCell.add(Box.createRigidArea(new Dimension(15, 0)));

            this.trackCell.add(new JLabel(resizeString(value.getArtist(), 35)));
            this.trackCell.add(Box.createRigidArea(new Dimension(15, 0)));

            this.trackCell.add(new JLabel(resizeString(value.getAlbum(), 35)));
            this.trackCell.add(Box.createRigidArea(new Dimension(15, 0)));

            Duration duration = value.getDuration();
            int minutes = duration.toMinutesPart();
            int seconds = duration.toSecondsPart();

            this.trackCell.add(
                new JLabel(
                    resizeString(
                        String.format("%02d:%02d", minutes, seconds),
                        8
                    )
                )
            );
            this.trackCell.add(Box.createHorizontalGlue());

            return this.trackCell;
        }
    }
}
