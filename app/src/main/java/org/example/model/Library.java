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

    private void recurseLibrary(File[] files) {
        Arrays.sort(files);

        for (File file : files) {
            if (file.isFile()) {
                this.playlist.appendTrack(new Track(file));
            } else if (file.isDirectory()) {
                recurseLibrary(file.listFiles());
            }
        }
    }

    public void populateLibrary() {
        File[] files = this.libraryDir.listFiles();
        recurseLibrary(files);
        fireIntervalAdded(this, 0, this.playlist.getSize());
    }

    public Track getElementAt(int index) {
        return this.playlist.getTrackAtIndex(index);
    }

    public int getSize() {
        return this.playlist.getSize();
    }

    public void clear() {
        fireIntervalRemoved(this, 0, this.playlist.getSize());
        this.playlist.clear();
    }
}
