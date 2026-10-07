package org.example.shared;

import java.time.Duration;
import java.util.Comparator;
import javax.swing.ImageIcon;

public class Track implements Comparator<Track> {

    String title;
    String artist;
    String album;

    Duration duration;
    ImageIcon cover;

    public Track(String title) {
        this.title = title;
    }

    /*
    public Track(File file) {
        read metadata and populate fields
    }
    */

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String toString() {
        return String.format("%s - %s:%s", this.artist, this.title, this.album);
    }

    public int compare(Track t1, Track t2) {
        int titleCompare = t1.title.compareTo(t2.title);

        return titleCompare;
    }
}
