package org.example;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import org.example.controller.LibraryController;
import org.example.controller.PlayerController;
import org.example.model.Library;
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

            Font firaCode = Font.createFont(
                Font.TRUETYPE_FONT,
                fontFile
            ).deriveFont(22f);
            UIManager.getDefaults().put("defaultFont", firaCode);

            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (
            UnsupportedLookAndFeelException
            | IOException
            | FontFormatException
            | URISyntaxException e
        ) {
            e.printStackTrace();
        }

        this.frame = new JFrame("JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);
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

        LibraryController libraryController = new LibraryController();
        // PlaylistController playlistController = new PlaylistController();
        PlayerController playerController = new PlayerController();

        JTabbedPane mainTabs = new JTabbedPane();
        mainTabs.addTab("Library", libraryController.getView());
        // maintabs.addTab("Playlist", PlayListController.getView());

        frame.add(mainTabs, BorderLayout.CENTER);
        frame.add(playerController.getView(), BorderLayout.SOUTH);

        app.displayFrame();

        // Library lib = new Library();
        // lib.setPath("/home/jawi/Pictures");
        // lib.populateLibrary();
    }
}
