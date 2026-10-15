package org.example.controller;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import org.example.model.PlaylistModel;
import org.example.shared.Playlist;
import org.example.shared.Track;
import org.example.view.PlaylistView;

public class PlaylistController {

    PlaylistModel model;
    PlaylistView view;

    public PlaylistController() {
        this.model = new PlaylistModel();
        this.view = new PlaylistView();
        this.view.setPlaylistContent(this.model);

        this.view.addFileChooserButtonListener(
            new FileChooserButtonListener(false),
            "saveFileButton"
        );

        this.view.addFileChooserButtonListener(
            new FileChooserButtonListener(true),
            "loadFileButton"
        );
    }

    public PlaylistView getView() {
        return this.view;
    }

    public List<Track> getSelectedTracks() {
        return this.view.getSelectedTracks();
    }

    public void appendTracks(List<Track> tracks) {
        this.model.appendTracks(tracks);
    }

    public void insertTracks(List<Track> tracks, int targetIndex) {
        this.model.insertTracks(tracks, targetIndex);
    }

    public void prependTracks(List<Track> tracks) {
        this.model.prependTracks(tracks);
    }

    class FileChooserButtonListener implements ActionListener {

        boolean isLoadOperation;

        public FileChooserButtonListener(boolean isLoadOperation) {
            this.isLoadOperation = isLoadOperation;
        }

        public void actionPerformed(ActionEvent arg0) {
            String filePath = view.selectFile(this.isLoadOperation);

            if (!filePath.substring(filePath.length() - 4).equals(".pll")) {
                filePath += ".pll";
            }

            if (this.isLoadOperation) {
                model.loadPlaylist(filePath);
            } else {
                model.savePlaylist(filePath);
            }

            view.setPlaylistLabelText(filePath);
        }
    }
}
