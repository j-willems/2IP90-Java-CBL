package org.example.model;

import java.io.*;
import java.util.*;
import javax.swing.*;
import org.example.shared.Playlist;
import org.example.shared.Track;

public class Library extends AbstractListModel<Track> {

    Playlist playlist;
    File libraryDir;

    public Library() {
        this.playlist = new Playlist();
        this.libraryDir = null;
    }

    public int getSize() {
        return this.playlist.getSize();
    }

    public Track getElementAt(int index) {
        return this.playlist.getTrackAtIndex(index);
    }

    public void clear() {
        fireIntervalRemoved(this, 0, this.playlist.getSize());
        this.playlist.clear();
    }

    public boolean setPath(String path) {
        try {
            this.libraryDir = new File(path);
        } catch (NullPointerException e) {
            return false;
        }

        if (!(this.libraryDir.exists() & this.libraryDir.isDirectory())) {
            return false;
        }

        return true;
    }

    public void populateLibrary() {
        File[] files = this.libraryDir.listFiles();
        recurseDirectory(files);
        fireIntervalAdded(this, 0, this.playlist.getSize());
    }

    private void recurseDirectory(File[] files) {
        Arrays.sort(files);

        for (File file : files) {
            if (file.isFile()) {
                if (Track.isSupportedFileType(file)) {
                    this.playlist.appendTrack(new Track(file));
                }
            } else if (file.isDirectory()) {
                recurseDirectory(file.listFiles());
            }
        }
    }
}
