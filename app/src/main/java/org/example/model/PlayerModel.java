package org.example.model;

import org.example.shared.Playlist;
import org.example.shared.Track;

public class PlayerModel {

    Playlist playlist;
    Track currentTrack;
    boolean isPlaying;
    boolean isLooping;

    public PlayerModel() {
        this.playlist = new Playlist();
        this.currentTrack = this.playlist.getTrackAtIndex(0);
        this.isPlaying = false;
        this.isLooping = false;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public boolean isLooping() {
        return this.isLooping;
    }

    public void setPlaying(boolean play) {
        this.isPlaying = play;
    }

    public void setLooping(boolean loop) {
        this.isLooping = loop;
    }

    public void nextTrack() {
        this.currentTrack = playlist.nextTrack();
    }

    public void previousTrack() {
        this.currentTrack = playlist.previousTrack();
    }
}
