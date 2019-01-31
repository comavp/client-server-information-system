package model;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class Genre implements Serializable, Comparable {
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        return this.name.equals(((Genre)o).getName());
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.name.compareTo(((Genre)o).getName());
    }
}
