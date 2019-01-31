package view.fxview.controllers;

import javafx.beans.property.SimpleStringProperty;

public class TableLine {
    private SimpleStringProperty genreName;
    private SimpleStringProperty trackName;
    private SimpleStringProperty artist;
    private SimpleStringProperty album;
    private SimpleStringProperty length;

    public TableLine(String genreName, String trackName, String artist, String album, String length) {
        this.genreName = new SimpleStringProperty(genreName);
        this.trackName = new SimpleStringProperty(trackName);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.length = new SimpleStringProperty(length);
    }

    public String getGenreName() {
        return genreName.get();
    }

    public String getTrackName() {
        return trackName.get();
    }

    public String getArtist() {
        return artist.get();
    }

    public String getAlbum() {
        return album.get();
    }

    public String getLength() {
        return length.get();
    }

    public SimpleStringProperty getGenreNameProperty() {
        return genreName;
    }

    public SimpleStringProperty getTrackNameProperty() {
        return trackName;
    }

    public SimpleStringProperty getArtistProperty() {
        return artist;
    }

    public SimpleStringProperty getAlbumProperty() {
        return album;
    }

    public SimpleStringProperty getLengthProperty() {
        return length;
    }

    public String toString() {
        return genreName.get() + "|" + trackName.get() + "|" + artist.get() + "|" + album.get() + "|" + length.get();
    }
}
