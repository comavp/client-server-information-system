package model;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

public interface ModelLoader {

    void saveModel(TreeMap<Genre, ArrayList<Track>> model) throws IOException, ClassNotFoundException;
    TreeMap<Genre, ArrayList<Track>> getModel() throws IOException, ClassNotFoundException;
    void addModel(Genre genre, Track track) throws IOException, ClassNotFoundException;
    void changeModel(String newGenreName, Track newTrack, Genre genre, Integer indexOfTrack) throws IOException, ClassNotFoundException;
    void deleteModel(Genre genre, Track track) throws IOException, ClassNotFoundException;
    void getGenreList() throws IOException, ClassNotFoundException;
    String getResult();
}
