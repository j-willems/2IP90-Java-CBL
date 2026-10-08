package org.example.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

public class LibraryView extends JPanel {
    JPanel folderBar;
    JLabel pathLabel;
    JFileChooser folderChooser;
    JButton folderChooserButton;

    JPanel contentPane;

    JButton stylizedButton(String buttonText) {
        JButton button = new JButton(buttonText);
        Dimension size = new Dimension(44, 44);

        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setFocusPainted(false);

        return button;
    }

    public LibraryView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(0, 15, 0, 15)));
        this.setPreferredSize(new Dimension(0, 55));

        this.folderBar = new JPanel();
        this.folderBar.setLayout(new BoxLayout(this.folderBar, BoxLayout.X_AXIS));
        this.folderBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

        this.pathLabel = new JLabel("Open a folder ->");
        this.folderChooserButton = stylizedButton("");

        this.folderBar.add(this.pathLabel);
        this.folderBar.add(Box.createHorizontalGlue());
        this.folderBar.add(this.folderChooserButton);

        this.add(this.folderBar);

        this.folderChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        this.folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public void setPathLabelText(String labelText) {
        this.pathLabel.setText(labelText);
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
}
