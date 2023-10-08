package aau;

import java.time.*;

public class Reservation {
    private Instant startTime = Instant.now();
    private Instant endTime;
    private Duration duration;

    public Reservation(TimePeriod timePeriod) {
        switch (timePeriod) {
            case HALF_HOUR:
                this.duration = Duration.ofSeconds(30 * 60);
                break;
            case HOUR:
                this.duration = Duration.ofSeconds(60 * 60);
                break;
            case ONE_AND_HALF_HOUR:
                this.duration = Duration.ofSeconds(90 * 60);
                break;
            case TWO_HOURS:
                this.duration = Duration.ofSeconds(120 * 60);
                break;
            default:
                break;
        }

        this.endTime = this.startTime.plus(this.duration);
    }

    public enum TimePeriod {
        HALF_HOUR,
        HOUR,
        ONE_AND_HALF_HOUR,
        TWO_HOURS,
    }
}
