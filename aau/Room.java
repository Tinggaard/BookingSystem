package aau;
import java.time.LocalDateTime;

public class Room {
    String name; // name of grouproom
    LocalDateTime created = LocalDateTime.now(); // time instatiated

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
