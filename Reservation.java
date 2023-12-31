import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservation implements TimeSlot, Comparable<Reservation> {

    public Group group;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean approved = false;

    Reservation(Group group, LocalDateTime start, LocalDateTime end) {
        this.group = group;
        startDate = start;
        endDate = end;
    }

    public void approve() {
        approved = true;
        group.addTime(ChronoUnit.MINUTES.between(startDate, endDate));
    }

    @Override
    public String toString() {
        return "Reservation: {" + startDate.toString() + " to " + endDate.toString() + " by " + group.toString() + "}";
    }

    @Override
    public boolean overlaps(TimeSlot other) {
        return !(this.startDate.isAfter(other.getEndDate()) ||
                this.endDate.isBefore(other.getStartDate()));
    }

    @Override
    public boolean covers(TimeSlot other) {
        return (this.startDate.isBefore(other.getStartDate()) &&
                this.endDate.isAfter(other.getEndDate()));
    }

    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public boolean isAvailable() {
        return !approved;
    }

    @Override
    public int compareTo(Reservation other) {
        return group.compareTo(other.group);
    }
}
