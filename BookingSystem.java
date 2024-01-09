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
        system.loadState();
        system.handleRequests();
        system.showCalendars();
        // system.addGroup();
        // system.bookInAdvance();
    }

    public void bookInAdvance() {
        System.out.println("BOOK ROOM");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide a group ID");
        String groupId = scanner.nextLine().trim();
        System.out.println("Please provide the groups course");
        String courseId = scanner.nextLine().trim();
        Group group = findGroup(groupId, courseId);
        if (group == null) {
            scanner.close();
            System.err.println("Group not found");
            return;
        }

        System.out.println("Please provide a room ID");
        String roomId = scanner.nextLine().trim();
        Room room = findRoom(roomId);
        if (room == null) {
            scanner.close();
            System.err.println("Room does not exist");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        System.out.println("Please provide a starting time in the format yy-MM-ddTHH:mm");
        System.out.println("E.g. 2024-01-10T16:00");

        LocalDateTime start, end;
        try {
            String startingTimeStr = scanner.nextLine().trim();
            start = LocalDateTime.parse(startingTimeStr, formatter);

            System.out.println("Please provide an end time in the format yy-MM-ddTHH:mm");
            String endingTimeStr = scanner.nextLine().trim();
            end = LocalDateTime.parse(endingTimeStr, formatter);
        } catch (Exception e) {
            scanner.close();
            System.err.println("Could not parse timedate.");
            return;
        }
        scanner.close();

        if (start.isAfter(end)) {
            System.err.println("End time cannot be after start time");
            return;
        }

        Reservation reservation = new Reservation(group, start, end);
        requestedReservations.computeIfAbsent(room, _k -> new ArrayList<Reservation>()).add(reservation);
    }

    public void showCalendars() {
        for (Room room : rooms) {
            System.out.println("Room:" + room.getIdentifier());
            System.out.println(room.toString());
        }
    }

    public void handleRequests() {
        for (Room room : requestedReservations.keySet()) {
            List<Reservation> allReservations = requestedReservations.get(room);

            while (allReservations.size() > 0) {
                Reservation candidate = allReservations.get(0);
                int index = 1;
                int candidateIndex = 0;
                for (Reservation other : allReservations.subList(1, allReservations.size())) {
                    if (candidate.overlaps(other) && candidate.compareTo(other) > 0) {
                        candidate = other;
                        candidateIndex = index;
                    }
                    index++;
                }

                candidate.approve();
                requestedReservations.get(room).remove(candidateIndex);
                index = rooms.indexOf(room);
                try {
                    rooms.get(index).book(candidate);
                } catch (Exception e) {
                    // we do nothing, the room is just not booked.
                }
            }
        }
    }

    public void loadState() {
        try {
            loadRooms("input/rooms.csv");
            loadGroups("input/groups.csv");
            loadReservations("input/reservations.csv");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
            String courseId = st.nextToken();
            String groupId = st.nextToken();
            String startTime = st.nextToken();
            String endTime = st.nextToken();

            Room r = findRoom(roomId);
            if (r == null) {
                s.close();
                throw new Exception("Room not found" + roomId);
            }

            Group g = findGroup(groupId, courseId);
            if (g == null) {
                s.close();
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
        for (String course : groups.keySet()) {
            for (Group group : groups.get(course)) {
                group.clearTime();
            }
        }
    }

    public void addGroup() {
        System.out.println("ADD NEW GROUP");
        System.out.println("Please provide a group ID");
        Scanner scanner = new Scanner(System.in);
        String groupId = scanner.nextLine().trim();

        System.out.println("Please provide a course ID");
        String courseId = scanner.nextLine().trim();

        System.out.println("Please provide the student IDs as integers seperated by spaces");
        String studentIdsStr = scanner.nextLine().trim();
        StringTokenizer st = new StringTokenizer(studentIdsStr, " ");
        List<Integer> studentIds = new ArrayList<>();
        while (st.hasMoreTokens()) {
            try {
                studentIds.add(Integer.parseInt(st.nextToken()));
            } catch (Exception e) {
                scanner.close();
                System.err.println("Could not parse integer.");
                return;
            }
        }

        Group g = new Group(groupId, courseId, studentIds);
        groups.computeIfAbsent(courseId, _k -> new ArrayList<Group>()).add(g);

        scanner.close();
    }

    private Room findRoom(String roomId) {
        for (Room room : rooms) {
            if (room.getIdentifier().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    private Group findGroup(String groupId, String courseId) {
        for (Group group : groups.get(courseId)) {
            if (group.id.equals(groupId)) {
                return group;
            }
        }
        return null;
    }
}
