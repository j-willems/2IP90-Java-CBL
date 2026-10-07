package org.example;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import org.example.controller.PlayerController;
import org.example.shared.Playlist;
import org.example.shared.Track;

public class App {

    static JFrame frame;

    public App() {
        try {
            File fontFile = new File(
                this.getClass()
                    .getClassLoader()
                    .getResource("FiraCodeNerdFontPropo-Regular.ttf")
                    .toURI()
            );

            Font firaCode = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            UIManager.getDefaults().put("defaultFont", firaCode);
        } catch (IOException | FontFormatException | URISyntaxException e) {
            e.printStackTrace();
        }

        this.frame = new JFrame("JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    public void displayFrame() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1294, 700);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();

        PlayerController playerController = new PlayerController();

        frame.add(playerController.getView(), BorderLayout.SOUTH);

        app.displayFrame();
    }
}
