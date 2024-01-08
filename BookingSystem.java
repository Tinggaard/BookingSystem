import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingSystem {
    private List<Room> rooms = new ArrayList<>();
    private Map<String, List<Group>> groups = new HashMap<String, List<Group>>(); // group by course
    private Map<Room, List<Reservation>> requestedReservations = new HashMap<Room, List<Reservation>>();

    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        try {
            system.loadState();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookInAdvance() {

    }

    public void showCalendars() {

    }

    public void handleRequests() {

    }

    public void loadState() throws Exception {
        loadRooms("input/rooms.csv");
        loadGroups("input/groups.csv");
        loadReservations("input/reservations.csv");
    }

    private void loadRooms(String filepath) throws FileNotFoundException {
        FileReader fr = new FileReader(filepath);
        Scanner s = new Scanner(fr);
        while (s.hasNextLine()) {
            String roomId = s.nextLine().strip();
            rooms.add(new Room(roomId));
        }
        s.close();
    }

    private void loadGroups(String filepath) throws FileNotFoundException {
        FileReader fr = new FileReader(filepath);
        Scanner s = new Scanner(fr);
        while (s.hasNextLine()) {
            String line = s.nextLine().strip();
            StringTokenizer st = new StringTokenizer(line, ",");
            if (st.countTokens() == 3) {
                String courseId = st.nextToken();
                String groupId = st.nextToken();
                int noMembers = Integer.parseInt(st.nextToken());

                // iterate the students
                List<Integer> studentIds = new ArrayList<>();
                for (int i = 0; i < noMembers; i++) {
                    String linegroup = s.nextLine().strip();
                    StringTokenizer stg = new StringTokenizer(linegroup, ",");
                    studentIds.add(Integer.parseInt(stg.nextToken()));
                }
                Group g = new Group(groupId, courseId, studentIds);

                groups.computeIfAbsent(courseId, _k -> new ArrayList<Group>()).add(g);
            }
        }
        s.close();
    }

    private void loadReservations(String filepath) throws Exception {
        FileReader fr = new FileReader(filepath);
        Scanner s = new Scanner(fr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        while (s.hasNextLine()) {
            String line = s.nextLine().strip();
            StringTokenizer st = new StringTokenizer(line, ",");
            String roomId = st.nextToken();
            String courseId = st.nextToken(); // this is not used?
            String groupId = st.nextToken();
            String startTime = st.nextToken();
            String endTime = st.nextToken();

            Room r = null;
            for (Room room : rooms) {
                if (room.getIdentifier().equals(roomId)) {
                    r = room;
                    break;
                }
            }
            if (r == null) {
                throw new Exception("Room not found" + roomId);
            }

            Group g = null;
            for (Group group : groups.get(courseId)) {
                if (group.id.equals(groupId)) {
                    g = group;
                    break;
                }
            }
            if (g == null) {
                throw new Exception("Group not found" + groupId);
            }

            LocalDateTime start = LocalDateTime.parse(startTime, formatter);
            LocalDateTime end = LocalDateTime.parse(endTime, formatter);
            Reservation resv = new Reservation(g, start, end);
            requestedReservations.computeIfAbsent(r, _k -> new ArrayList<Reservation>()).add(resv);
        }
        s.close();
    }

    public void startNewReservationPeriod() {

    }

    public void addGroup() {

    }
}
