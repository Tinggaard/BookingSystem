package aau;

import java.util.ArrayList;

public class Room {
    static ArrayList<Room> allRooms = new ArrayList<Room>();
    // static ArrayList<Room> reservedRooms = new ArrayList<Room>();

    String name; // name of grouproom
    private boolean reserved = false;
    private Group reservedByGroup;

    // consturtors

    public Room(String name) {
        this.name = name;
        if (allRooms.contains(this)) {
            return;
        }
        allRooms.add(this);
    }

    // static methods

    public static boolean reserveNextAvailable(Group group) {
        for (Room room : allRooms) {
            // check if group already has reserved a room
            if (room.reservedByGroup == group) {
                return false;
            }

            // if room is not reserved, reserve it
            if (!room.reserved) {
                room.reserve(group);
                return true;
            }
        }
        // no available rooms were found
        return false;
    }

    // overrides

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Room)) {
            return false;
        }
        return this.name == ((Room) other).name;
    }

    public String toString() {
        return this.name;
    }

    // methods

    public void reserve(Group group) {
        this.reserved = true;
        this.reservedByGroup = group;
    }
}
