package org.example.model;

import org.example.shared.Playlist;
import org.example.shared.Track;

public class MediaPlayer {

    Playlist playlist;
    Track currentTrack;
    boolean isPlaying;
    boolean isLooping;

    // long msPosition;

    public MediaPlayer() {
        this.playlist = new Playlist();
        this.currentTrack = this.playlist.firstTrack();
        this.isPlaying = false;
        // this.msPositon = 0;
    }

    public void nextTrack() {
        this.currentTrack = playlist.nextTrack();
    }

    public void previousTrack() {
        this.currentTrack = playlist.previousTrack();
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public boolean isLooping() {
        return this.isLooping;
    }

    public void setPlaying(boolean play) {
        this.isPlaying = play;

        // TODO
        // play the song, starting from this.msPosition
        // pause the song and save this.msPosition
    }

    /*
    getPositon()
    setPositoion()
    */

    public void setLooping(boolean loop) {
        this.isLooping = loop;
    }

    public Track getCurrentTrack() {
        return this.currentTrack;
    }

    public void setCurrentTrack(Track track) {
        this.currentTrack = track;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void setPlaylist(Playlist list) {
        this.playlist = list;
    }
}
