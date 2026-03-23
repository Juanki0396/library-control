package pio.daw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library implements Controlable {
    private Map<String,User> users;

    /**
     * Read the library register file (.txt) and create a library object
     * with the current status of the users.
     * @param path Library registry file path.
     * @return Library object.
     */
    public static Library fromFile(Path path) throws IOException{
        Library library = new Library();
        for( String line : Files.readAllLines(path)){
            String[] splittedLine = line.split(";");
            String id = splittedLine[0];
            EventType e = 
                switch(splittedLine[1]) {
                    case "ENTRADA" -> EventType.ENTRY;
                    case "SALIDA" -> EventType.EXIT;
                    default -> null;
                };
            library.registerChange(id, e);
        }         
        return library;
    }

    private Library(){
        this.users = new HashMap<>();
    }

    public void registerChange(String id, EventType e){
        User u = this.users.get(id);
        if(u == null){
            u = new User(id);
        }
        u.registerNewEvent(e);
        this.users.put(id, u);
        
    }

    public List<User> getCurrentInside(){
         return this.users.values().stream()
                .filter(u -> u.isInside())
                .collect(Collectors.toList());
    }

    public  List<User> getMaxEntryUsers(){
        Integer maxEntries = 0;
        List<User> maxUsers = new ArrayList<>();
        for(User u : this.users.values()){
            if(u.getNEntries() == maxEntries){
                maxUsers.add(u);
            }
            else if(u.getNEntries() > maxEntries){
                maxEntries = u.getNEntries();
                maxUsers = new ArrayList<>();
                maxUsers.add(u);
            }
        }
        return maxUsers;
    }


    /**
     * Get the list with all the users that has enter the place ordered by User ID.
     * @return
     */
    public List<User> getUserList(){
        return this.users.values().stream()
               .filter(u -> u.getNEntries() > 0)
               .sorted((u1,u2) -> u1.getId().compareTo(u2.getId()))
               .collect(Collectors.toList());
    }

    /**
     * Print a resume of the current status:
     * 1. Current users
     * 2. Entries per user
     * 3. User with more entries
     */
    public void printResume(){
        System.out.println("Usuarios actualmente dentro de la biblioteca:");
        this.getCurrentInside().stream()
            .sorted( (u1, u2) -> u1.getId().compareTo(u2.getId()) )
            .forEach( (u) -> { System.out.println(u.getId()); });
        System.out.println();
        System.out.println("Número de entradas por usuario:");
        this.getUserList().stream()
            .sorted( (u1, u2) -> u1.getId().compareTo(u2.getId()) )
            .forEach( (u) -> { System.out.printf("%s -> %d\n", u.getId(), u.getNEntries()); });
        System.out.println();
        System.out.println("Usuario(s) con más entradas:");
        this.getMaxEntryUsers().stream()
            .sorted( (u1, u2) -> u1.getId().compareTo(u2.getId()) )
            .forEach( (u) -> { System.out.println(u.getId()); });
    }
}
