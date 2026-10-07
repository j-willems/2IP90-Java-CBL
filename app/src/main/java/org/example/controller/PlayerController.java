package org.example.controller;

import java.awt.event.*;
import org.example.model.MediaPlayer;
import org.example.view.PlayerView;

public class PlayerController {

    MediaPlayer model;
    PlayerView view;

    public PlayerController() {
        this.model = new MediaPlayer();
        this.view = new PlayerView();

        this.view.addPlayPauseButtonListener(new PlayPauseButtonListener());
        this.view.addLoopButtonListener(new LoopButtonListener());
    }

    public PlayerView getView() {
        return this.view;
    }

    class PlayPauseButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            boolean isPlaying = model.isPlaying();
            model.setPlaying(!isPlaying);

            if (isPlaying) {
                view.changePlayPauseButtonText("󰐊");
            } else {
                view.changePlayPauseButtonText("󰏤");
            }
        }
    }

    class LoopButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            boolean isLooping = model.isLooping();
            model.setLooping(!isLooping);

            if (isLooping) {
                view.changeLoopButtonText("󰑖");
            } else {
                view.changeLoopButtonText("󰑗");
            }
        }
    }
}
