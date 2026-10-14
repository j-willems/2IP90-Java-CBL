package org.example.shared;

import java.io.*;
import java.time.*;
import java.util.*;
import javax.swing.*;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.tag.*;

public class Track implements Comparator<Track> {

    String title;
    String artist;
    String album;

    Duration duration;
    ImageIcon cover;

    public Track(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            Tag tag = audioFile.getTag();

            this.artist = "";
            this.title = tag.getFirst(FieldKey.TITLE);
            this.album = tag.getFirst(FieldKey.ALBUM);
            this.duration = Duration.ofSeconds(
                audioFile.getAudioHeader().getTrackLength()
            );

            List<TagField> artists = tag.getFields(FieldKey.ARTIST);
            for (TagField artist : artists) {
                this.artist += artist.toString();
            }

            // TagField coverArt = tag.getFirstField(FieldKey.COVER_ART);
        } catch (
            CannotReadException
            | IOException
            | TagException
            | ReadOnlyFileException
            | InvalidAudioFrameException e
        ) {
            e.printStackTrace();
        }
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
        return String.format(
            "song [%s] by [%s] on album [%s]",
            this.title,
            this.artist,
            this.album
        );
    }

    private static String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");

        if (dotIndex >= 0) {
            return filename.substring(dotIndex + 1);
        }

        return "";
    }

    public static boolean isSupportedFileType(File file) {
        String[] supportedFiles = { "mp3", "flac", "ogg", "aiff", "wav" };
        String extension = getFileExtension(file.getName());

        return Arrays.asList(supportedFiles).contains(extension);
    }

    public int compare(Track t1, Track t2) {
        int titleCompare = t1.title.compareTo(t2.title);

        return titleCompare;
    }
}
