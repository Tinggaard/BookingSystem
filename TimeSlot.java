/**
 * An interface TimeSlot that represents time slots that can be booked
 *
 * @author Gabriela Montoya
 * @version 1.1, 2023-12-04
 */
 
import java.time.LocalDateTime;
 
public interface TimeSlot {

    boolean overlaps(TimeSlot other);
    boolean covers(TimeSlot other);

    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    
    boolean isAvailable();
}