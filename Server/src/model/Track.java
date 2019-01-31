package model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Track implements Serializable {

    private String title;
    private  String artist;
    private String album;
    private int length;

    public Track(String title, String artist, String album, int length) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean equals(Object o) {
        return (this.title.equals(((Track)o).getTitle()) && this.artist.equals(((Track)o).getArtist()) && this.album.equals(((Track)o).getAlbum())
                && this.length == ((Track)o).getLength());
    }
}
