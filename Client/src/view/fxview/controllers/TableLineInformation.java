package view.fxview.controllers;

public class TableLineInformation {

    private int number;
    private String line;

    public TableLineInformation(int number, String line) {
        this.number = number;
        this.line = line;
    }

    public int getNumber() {
        return number;
    }

    public String getLine() {
        return line;
    }

    public boolean equals(Object o) {
        return number == ((TableLineInformation)o).number && ((TableLineInformation)o).line.equals(line);
    }
}
