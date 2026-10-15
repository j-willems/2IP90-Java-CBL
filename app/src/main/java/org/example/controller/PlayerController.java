package org.example.controller;

import java.awt.event.*;
import org.example.controller.LibraryController;
import org.example.controller.PlaylistController;
import org.example.model.PlayerModel;
import org.example.shared.Playlist;
import org.example.shared.Track;
import org.example.view.PlayerView;

public class PlayerController {

    PlayerModel model;
    PlayerView view;
    LibraryController libraryController;
    PlaylistController playlistController;

    public PlayerController(
        LibraryController libraryController,
        PlaylistController playlistController
    ) {
        this.model = new PlayerModel();
        this.view = new PlayerView();
        this.libraryController = libraryController;
        this.playlistController = playlistController;

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
            new AddPlaylistButtonListener("prepend")
        );
        this.view.addButtonListener(
            "insertPlaylistButton",
            new AddPlaylistButtonListener("insert")
        );
        this.view.addButtonListener(
            "appendPlaylistButton",
            new AddPlaylistButtonListener("append")
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

        String buttonId;

        public AddPlaylistButtonListener(String buttonId) {
            this.buttonId = buttonId;
        }

        public void actionPerformed(ActionEvent arg0) {
            switch (this.buttonId) {
                case "append":
                    playlistController.appendTracks(
                        libraryController.getSelectedTracks()
                    );
                    break;
                case "insert":
                    playlistController.insertTracks(
                        libraryController.getSelectedTracks(),
                        view.askForInsertionIndex()
                    );
                    break;
                case "prepend":
                    playlistController.prependTracks(
                        libraryController.getSelectedTracks()
                    );
            }
        }
    }
}
