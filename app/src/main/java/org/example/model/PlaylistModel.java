package org.example.model;

import java.io.*;
import java.util.*;
import javax.swing.*;
import org.example.shared.Playlist;
import org.example.shared.Track;

public class PlaylistModel extends AbstractListModel<Track> {

    Playlist playlist;

    public PlaylistModel() {
        this.playlist = new Playlist();
    }

    public int getSize() {
        return this.playlist.getSize();
    }

    public Track getElementAt(int index) {
        return this.playlist.getTrackAtIndex(index);
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void clear() {
        fireIntervalRemoved(this, 0, this.playlist.getSize());
        this.playlist.clear();
    }

    public void prependTracks(List<Track> tracks) {
        if (tracks.isEmpty()) {
            return;
        }

        for (Track track : tracks) {
            this.playlist.prependTrack(track);
        }

        fireIntervalAdded(this, 0, this.playlist.getSize());
    }

    public void insertTracks(List<Track> tracks, int targetIndex) {
        if (
            tracks.isEmpty() ||
            targetIndex < 0 ||
            targetIndex > this.playlist.getSize()
        ) {
            return;
        }

        int counter = 0;

        for (Track track : tracks) {
            this.playlist.insertTrack(track, targetIndex + counter);

            counter++;
        }

        fireContentsChanged(this, 0, this.playlist.getSize());
    }

    public void appendTracks(List<Track> tracks) {
        if (tracks.isEmpty()) {
            return;
        }

        int startIndex = this.playlist.getSize();

        for (Track track : tracks) {
            this.playlist.appendTrack(track);
        }

        fireIntervalAdded(this, startIndex + 1, this.playlist.getSize());
    }

    public void savePlaylist(String filePath) {
        String output = this.playlist.getStringRepresentation();

        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, false)
            );

            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPlaylist(String filePath) {
        this.clear();

        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(filePath)
            );

            String entry;
            while ((entry = reader.readLine()) != null) {
                File trackFile = new File(entry);

                this.playlist.appendTrack(new Track(trackFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
