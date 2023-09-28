package aau;
import java.time.LocalDateTime;

public class Grouproom {
    String name; // name of grouproom
    LocalDateTime created = LocalDateTime.now(); // time instatiated

    enum TimePeriod {
        HALF_HOUR,
        HOUR,
        ONE_AND_HALF_HOUR,
        TWO_HOURS,
    }

    public Grouproom(String name) {
        this.name = name;
        System.out.println(created.toString());
    }

    public String getName() {
        return name;
    }
}
