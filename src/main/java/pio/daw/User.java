package pio.daw;

public class User implements Localizable {
    private String id;
    private Integer entrances = 0;
    private Integer exits = 0;

    public User(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    //TODO
}
