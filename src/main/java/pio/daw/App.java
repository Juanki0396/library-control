package pio.daw;

import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    /**
     * Parse the arguments of the program to get the library registry file
     * path. Exits the program if the args are not correct or the file does
     * not exists.
     * @param args program args.
     * @return Path to file if exists.
     */
    public static Path getPathFromArgs(String[] args){
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Usage: NombrePrograma <Ruta fichero>");
        }
        Path p = Path.of(args[0]);
        if (!Files.exists(p) || !Files.isRegularFile(p)) {
            throw new IllegalArgumentException("File does not exist: " + p.toString());
        }
        return p;
    }

    public static void main(String[] args) {
        Path p = getPathFromArgs(args);
        Controlable controler = Library.fromFile(p);
        controler.printResume();
    }
}
