package pio.daw;

import java.nio.file.Path;

public class App {
    public static Path getPathFromArgs(String[] args){
        //TODO
        return null;
    }

    public static void main(String[] args) {
        Path p = getPathFromArgs(args);
        Controlable controler = Library.fromFile(p);
        controler.printResume();
    }
}
