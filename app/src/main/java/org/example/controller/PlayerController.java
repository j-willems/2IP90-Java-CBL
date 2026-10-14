package org.example.controller;

import java.awt.event.*;
import org.example.controller.LibraryController;
//import org.example.controller.PlaylistController;
import org.example.model.MediaPlayer;
import org.example.shared.Track;
import org.example.view.PlayerView;

public class PlayerController {

    MediaPlayer model;
    PlayerView view;
    LibraryController libraryController;

    // PlaylistController playlistController;

    public PlayerController(LibraryController libraryController) {
        this.model = new MediaPlayer();
        this.view = new PlayerView();
        this.libraryController = libraryController;
        // this.playlistController = playlistController;

        this.view.addButtonListener(
            "previousButton",
            new NavigationButtonListener(false)
        );
        this.view.addButtonListener(
            "playPauseButton",
            new PlayPauseButtonListener()
        );
        this.view.addButtonListener(
            "nextButton",
            new NavigationButtonListener(true)
        );
        this.view.addButtonListener("loopButton", new LoopButtonListener());
        this.view.addButtonListener(
            "prependPlaylistButton",
            new AddPlaylistButtonListener(false)
        );
        this.view.addButtonListener(
            "appendPlaylistButton",
            new AddPlaylistButtonListener(true)
        );
    }

    public PlayerView getView() {
        return this.view;
    }

    class NavigationButtonListener implements ActionListener {

        boolean isNextButton;

        public NavigationButtonListener(boolean isNextButton) {
            this.isNextButton = isNextButton;
        }

        public void actionPerformed(ActionEvent arg0) {
            if (this.isNextButton) {
                model.nextTrack();
            } else {
                model.previousTrack();
            }
        }
    }

    class PlayPauseButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            boolean isPlaying = model.isPlaying();
            model.setPlaying(!isPlaying);

            if (isPlaying) {
                view.changeButtonText("playPauseButton", "󰐊");
            } else {
                view.changeButtonText("playPauseButton", "󰏤");
            }
        }
    }

    class LoopButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            boolean isLooping = model.isLooping();
            model.setLooping(!isLooping);

            if (isLooping) {
                view.changeButtonText("loopButton", "󰑖");
            } else {
                view.changeButtonText("loopButton", "󰑗");
            }
        }
    }

    class AddPlaylistButtonListener implements ActionListener {

        boolean isAppendButton;

        public AddPlaylistButtonListener(boolean isAppendButton) {
            this.isAppendButton = isAppendButton;
        }

        public void actionPerformed(ActionEvent arg0) {
            if (this.isAppendButton) {
                // append
                for (Track track : libraryController.getSelectedTracks()) {
                    System.out.println(track);
                }
            } else {
                // prepend
                for (Track track : libraryController.getSelectedTracks()) {
                    System.out.println(track);
                }
            }
        }
    }
}
