import java.time.LocalDateTime;

public class FreeSlot implements TimeSlot {

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    @Override
    public String toString() {
        return "FreeSlot: {" + startDate.toString() + " to " + endDate.toString() + "}";
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
        return true;
    }
}
