package pio.daw;

import java.nio.file.Path;

public class App {
    /**
     * Parse the arguments of the program to get the library registry file
     * path. Exits the program if the args are not correct or the file does
     * not exists.
     * @param args program args.
     * @return Path to file if exists.
     */
    public static Path getPathFromArgs(String[] args) throws Exception{
        if(args.length != 1){
            throw new Exception("Program args are no just 1: " + String.join(" ", args));
        }
        Path p = Path.of(args[0]);
        if(!p.toString().endsWith(".txt")){
            throw new Exception("The file is not valid: " + p.toString());
        }

        return p;
    }

    public static void main(String[] args) throws Exception{
        Path p = getPathFromArgs(args);
        Controlable controler = Library.fromFile(p);
        controler.printResume();
    }
}
