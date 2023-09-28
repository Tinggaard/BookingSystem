import aau.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner scanner = new Scanner(System.in);
        // String name = scanner.nextLine();
        // System.out.println(name);
        // scanner.close();

        List<Student> allStudents = new ArrayList<Student>();
        List<Course> allCourses = new ArrayList<Course>();
        List<Grouproom> allGrouprooms = new ArrayList<Grouproom>();
        List<Group> allGroups = new ArrayList<Group>();

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
            allGrouprooms.add(new Grouproom(room));
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

        System.out.println(String.format("There are %d students", Student.getCount()));
    }
}
