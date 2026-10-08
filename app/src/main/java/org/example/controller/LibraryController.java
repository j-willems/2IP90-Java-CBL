package org.example.controller;

import java.awt.event.*;
import java.io.*;
import org.example.model.Library;
import org.example.view.LibraryView;

public class LibraryController {

    Library model;
    LibraryView view;

    public LibraryController() {
        this.model = new Library();
        this.view = new LibraryView();

        this.view.addFolderChooserButtonListener(
            new FolderChooserButtonListener()
        );
    }

    public LibraryView getView() {
        return this.view;
    }

    class FolderChooserButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            String folderPath = view.selectFolder();
            model.setPath(folderPath);
            model.populateLibrary();

            view.setPathLabelText(folderPath);
        }
    }
}
