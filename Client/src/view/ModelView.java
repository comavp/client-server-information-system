package view;

import java.io.IOException;
import java.util.Observer;

public interface ModelView {

    void receiveResult(String arg);
    void execute() throws IOException;
    void addObserver(Observer o);
}
