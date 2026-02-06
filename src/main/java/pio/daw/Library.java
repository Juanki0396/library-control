package pio.daw;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Library implements Controlable {
    private Map<String,User> users;

    public static Library fromFile(Path path){
        //TODO
        return null;
    }

    private Library(Map<String,User> users){
        this.users = users;
    }

    //TODO
}
