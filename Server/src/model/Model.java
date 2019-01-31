package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    private ModelLoader loader = new Loader();

    private void notifyObserver(String result) {
        setChanged();
        notifyObservers(result);
        clearChanged();
    }

    public void helper(String request) {
        try {
            int i = -1;
            int tmp = 0;
            String[] argArray = new String[8];
            Genre genre;
            Track track;
            for (int j = 0; j < argArray.length; j++) {
                argArray[j] = "";
            }

            while (i < request.length()) {
                i++;
                while (i < request.length() && request.charAt(i) != '|') {
                    argArray[tmp] += request.charAt(i);
                    i++;
                }
                tmp++;
            }
            switch (argArray[0]) {
                case "1":
                    genre = new Genre(argArray[1]);
                    track = new Track(argArray[2], argArray[3], argArray[4], Integer.parseInt(argArray[5]));
                    loader.addModel(genre, track);
                    break;
                case "2":
                    genre = new Genre(argArray[1]);
                    track = new Track(argArray[2], argArray[3], argArray[4], Integer.parseInt(argArray[5]));
                    loader.deleteModel(genre, track);
                    break;
                case "3":
                    break;
                case "4":
                    genre = new Genre(argArray[6]);
                    track = new Track(argArray[2], argArray[3], argArray[4], Integer.parseInt(argArray[5]));
                    loader.changeModel(argArray[1], track, genre, Integer.parseInt(argArray[7]) - 1);
                    break;
                case "5":
                    loader.getGenreList();
                    break;
                case "6":
                    ((Loader)loader).mergeData(argArray[1]);
                    break;
            }
            notifyObserver(loader.getResult());
        }
        catch (IOException e) {
            notifyObserver("Произошла ошибка!(Файл не существует)");
        }
        catch (ClassNotFoundException e) {
            notifyObserver("Произошла ошибка!(ClassNotFoundException)");
        }
        catch (NullPointerException e) {
            notifyObserver("Произошла ошибка!(NullPointerException)");
        }
    }
}
