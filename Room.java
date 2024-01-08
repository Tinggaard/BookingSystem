import java.time.*;
import java.util.*;

public class Room implements Identifiable<String> {

    private String id;
    private Map<LocalDate, List<TimeSlot>> calendar = new HashMap<LocalDate,List<TimeSlot>>();


    Room(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Room)) {
            return false;
        }

        return hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "Room " + id;
    }

    public String toString(LocalDate date) {
        List<TimeSlot> cal = calendar.get(date);
        if (cal == null) {
            return "free all day";
        }

        String ret = "";
        for (TimeSlot ts : cal) {
            ret += ts.toString();
        }

        return ret;
    }

    public String getIdentifier() {
        return id;
    }

    public boolean available(TimeSlot t) {
        LocalDate date = t.getStartDate().toLocalDate();

        List<TimeSlot> cal = calendar.get(date);

        // date not initialized in calendar
        if (cal == null) {
            return true;
        }

        for (TimeSlot ts : cal) {
            if (ts.overlaps(t) && !ts.isAvailable()) {
                return false;
            }
        }

        return true;
    }

    public void book(Reservation r) throws Exception {
        if (!available(r)) {
            throw new Exception("Tried to reserve a timeslot that is already reserved." + r.toString());
        }

        LocalDate d = r.getStartDate().toLocalDate();
        calendar.computeIfAbsent(d, _k -> new ArrayList<TimeSlot>()).add(r);
    }
}
