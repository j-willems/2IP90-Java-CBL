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
                int index = this.playlist.appendTrack(new Track(file));
                fireIntervalAdded(this, index, index);
            } else if (file.isDirectory()) {
                recurseLibrary(file.listFiles());
            }
        }
    }

    public void populateLibrary() {
        File[] files = this.libraryDir.listFiles();
        recurseLibrary(files);

        this.playlist.traverse();
    }

    public Track getElementAt(int index) {
        System.out.println("requested element at idx: " + index);
        return this.playlist.getTrackAtIndex(index);
    }

    public int getSize() {
        System.out.println(
            "requested playlist size: " + this.playlist.getSize()
        );

        return this.playlist.getSize();
    }
}
