import java.time.LocalDateTime;

public class Reservation implements TimeSlot, Comparable<Reservation> {

    public Group group;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    Reservation(Group group, LocalDateTime start, LocalDateTime end) {
        this.group = group;
        startDate = start;
        endDate = end;
    }

    public void approve() {
        // TODO: update time used by group
        System.out.println("somebody approved a reservation!");
    }

    @Override
    public String toString() {
        return "Reservation: {" + startDate.toString() + " to " + endDate.toString() + "}";
    }

    public boolean overlaps(TimeSlot other) {
        return !(this.startDate.isAfter(other.getEndDate()) ||
                this.endDate.isBefore(other.getStartDate()));
    }

    public boolean covers(TimeSlot other) {
        return (this.startDate.isBefore(other.getStartDate()) &&
                this.endDate.isAfter(other.getEndDate()));
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isAvailable() {
        return false;
    }

    @Override
    public int compareTo(Reservation other) {
        return group.compareTo(other.group);
    }
}
