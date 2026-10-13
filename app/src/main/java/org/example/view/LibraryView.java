package org.example.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import org.example.shared.Track;

public class LibraryView extends JPanel {

    JPanel folderBar;
    JLabel pathLabel;
    JFileChooser folderChooser;
    JButton folderChooserButton;

    JPanel headerBar;

    JList tracksList;
    JScrollPane scrollPane;

    JButton stylizedButton(String buttonText) {
        JButton button = new JButton(buttonText);
        Dimension size = new Dimension(44, 44);

        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setFocusPainted(false);

        button.setFont(UIManager.getFont("defaultFont").deriveFont(21.5f));

        return button;
    }

    String resizeString(String input, int finalSize) {
        if (input == null) {
            return String.format("%-" + finalSize + "s", "");
        }

        if (input.length() > finalSize) {
            return input.substring(0, finalSize - 3) + "...";
        }

        return String.format("%-" + finalSize + "s", input);
    }

    public LibraryView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(0, 15, 0, 15)));

        this.folderBar = new JPanel();
        this.folderBar.setLayout(
            new BoxLayout(this.folderBar, BoxLayout.X_AXIS)
        );
        this.folderBar.setBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)
        );

        this.headerBar = new JPanel();
        this.headerBar.setLayout(
            new BoxLayout(this.headerBar, BoxLayout.X_AXIS)
        );
        this.headerBar.setMaximumSize(new Dimension(9999, 58));

        this.pathLabel = new JLabel("Library: no folder loaded");
        this.folderBar.add(this.pathLabel);

        this.folderBar.add(Box.createHorizontalGlue());

        this.folderChooserButton = stylizedButton("");
        this.folderBar.add(this.folderChooserButton);

        this.headerBar.add(Box.createRigidArea(new Dimension(6, 0)));

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

        this.folderChooser = new JFileChooser(
            FileSystemView.getFileSystemView().getHomeDirectory()
        );
        this.folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        this.tracksList = new JList<Track>();
        this.tracksList.setCellRenderer(new TrackRenderer());

        this.scrollPane = new JScrollPane(
            this.tracksList,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        this.add(this.folderBar);
        this.add(this.headerBar);
        this.add(this.scrollPane);
    }

    public void setLibraryContent(ListModel<Track> model) {
        this.tracksList.setModel(model);
    }

    public void setPathLabelText(String labelText) {
        this.pathLabel.setText("Library: " + labelText);
    }

    public String selectFolder() {
        this.folderChooser.showDialog(this, "Open Folder");
        return this.folderChooser.getSelectedFile().getAbsolutePath();
    }

    public void addFolderChooserButtonListener(
        ActionListener folderChooserButtonListener
    ) {
        this.folderChooserButton.addActionListener(folderChooserButtonListener);
    }

    class TrackRenderer extends JPanel implements ListCellRenderer<Track> {

        JPanel trackCell;

        public TrackRenderer() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        @Override
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

            this.trackCell.add(Box.createRigidArea(new Dimension(4, 0)));

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

            return this.trackCell;
        }
    }
}
