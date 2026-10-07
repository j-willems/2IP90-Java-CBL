package org.example.controller;

import org.example.model.MediaPlayer;
import org.example.view.PlayerView;

public class PlayerController {

    MediaPlayer model;
    PlayerView view;

    public PlayerController() {
        this.model = new MediaPlayer();
        this.view = new PlayerView();
    }

    public PlayerView getView() {
        return this.view;
    }
}
