package org.example.model;

import java.io.*;
import org.example.shared.Playlist;
import org.example.shared.Track;

public class Library {

    Playlist playlist;
    File libraryDir;

    public Library() {
        this.playlist = null;
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
        for (int i = 1; i < files.length; i++) {
            File file = files[i];

            if (file.isFile()) {
                System.out.println(file);
            } else if (file.isDirectory()) {
                recurseLibrary(file.listFiles());
            }
        }
    }

    public void populateLibrary() {
        File[] files = this.libraryDir.listFiles();
        recurseLibrary(files);
    }
}
