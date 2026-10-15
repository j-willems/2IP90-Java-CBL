package org.example.controller;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import org.example.model.LibraryModel;
import org.example.shared.Playlist;
import org.example.shared.Track;
import org.example.view.LibraryView;

public class LibraryController {

    LibraryModel model;
    LibraryView view;

    public LibraryController() {
        this.model = new LibraryModel();
        this.view = new LibraryView();
        this.view.setLibraryContent(this.model);

        this.view.addFolderChooserButtonListener(
            new FolderChooserButtonListener()
        );
    }

    public LibraryView getView() {
        return this.view;
    }

    public List<Track> getSelectedTracks() {
        return this.view.getSelectedTracks();
    }

    class FolderChooserButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            String folderPath = view.selectFolder();
            model.setPath(folderPath);

            model.clear();
            model.populateLibrary();

            view.setPathLabelText(folderPath);
        }
    }
}
