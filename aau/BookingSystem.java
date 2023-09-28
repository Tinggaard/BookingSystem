package aau;

import java.util.ArrayList;

public class BookingSystem {
    ArrayList<Student> allStudents = new ArrayList<Student>();
    ArrayList<Course> allCourses = new ArrayList<Course>();
    ArrayList<Room> allGrouprooms = new ArrayList<Room>();
    ArrayList<Group> allGroups = new ArrayList<Group>();

    public void init() {
        String[] names = {"jens", "martin", "thomas", "mikkel", "max", "armin"};
        for (String name : names) {
            allStudents.add(new Student(name));
        }

        String[] courses = {"OOP", "DEB", "SU", "DTG", "PBL", "SLIAL", "IMPR"};
        for (String course : courses) {
            allCourses.add(new Course(course));
        }

        String[] rooms = {"CAS", "FRB", "NOVI"};
        for (String room : rooms) {
            allGrouprooms.add(new Room(room));
        }

        String[] groups = {"P0", "P1", "P2", "P3"};
        int count = 0;
        for (String group : groups) {
            Group grp = new Group(group);
            for (Student student : allStudents.subList(count, count+3)) {
                grp.addStudent(student);
            }
            allGroups.add(grp);
            count++;
        }
    }


    public void addStudentToCourse() {

    }

    public boolean bookRoomForGroup(Group group) {
        return true;
    }

    public void addStudentToGroup(Student student, Course course) {

    }
}
