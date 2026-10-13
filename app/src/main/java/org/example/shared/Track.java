package org.example.shared;

import java.io.File;
import java.time.Duration;
import java.util.Comparator;
import javax.swing.ImageIcon;

public class Track implements Comparator<Track> {

    String title;
    String artist;
    String album;

    Duration duration;
    ImageIcon cover;

    public Track(File file) {
        this.title = file.getName();
        this.artist = file.getName();
        this.album = file.getName();
        this.duration = Duration.ZERO;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public String toString() {
        return String.format("%s - %s:%s", this.artist, this.title, this.album);
    }

    public int compare(Track t1, Track t2) {
        int titleCompare = t1.title.compareTo(t2.title);

        return titleCompare;
    }
}
