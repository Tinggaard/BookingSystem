import java.util.ArrayList;
import java.util.List;

public class Group implements Comparable<Group>, Identifiable<String> {

    public String id;
    public String courseId;
    public int minutesReserved = 0;
    public int noMembers;
    private List<Integer> studentIds = new ArrayList<>();

    Group(String id, String courseId, List<Integer> studentIds) {
        this.id = id;
        this.courseId = courseId;
        this.studentIds = studentIds;
        noMembers = studentIds.size();
    }

    @Override
    public String toString() {
        return id + studentIds.toString();
    }

    @Override
    public String getIdentifier() {
        return id;
    }

    public void addTime(double d) {
        addTime((int) d*60);
    }

    public void addTime(int minutes) {
        minutesReserved += minutes;
    }

    public void addTime(long minutes) {
        minutesReserved += (int) minutes;
    }

    public void clearTime() {
        minutesReserved = 0;
    }

    @Override
    public int compareTo(Group other) {
        int reservedDiff = minutesReserved - other.minutesReserved;
        return reservedDiff == 0 ? noMembers - other.noMembers : reservedDiff;
    }
}
