package aau;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
    // static ArrayList<Student> allStudents = new ArrayList<Student>();
    // static ArrayList<Course> allCourses = new ArrayList<Course>();
    // static ArrayList<Room> allGrouprooms = new ArrayList<Room>();
    // static ArrayList<Group> allGroups = new ArrayList<Group>();

    // public static void init() {
    //     String[] names = {"jens", "martin", "thomas", "mikkel", "max", "armin"};
    //     for (String name : names) {
    //         allStudents.add(new Student(name));
    //     }

    //     String[] courses = {"OOP", "DEB", "SU", "DTG", "PBL", "SLIAL", "IMPR"};
    //     for (String course : courses) {
    //         allCourses.add(new Course(course));
    //     }

    //     String[] rooms = {"CAS", "FRB", "NOVI"};
    //     for (String room : rooms) {
    //         allGrouprooms.add(new Room(room));
    //     }

    //     String[] groups = {"P0", "P1", "P2", "P3"};
    //     int count = 0;
    //     for (String group : groups) {
    //         Group grp = new Group(group);
    //         for (Student student : allStudents.subList(count, count+3)) {
    //             grp.addStudent(student);
    //         }
    //         allGroups.add(grp);
    //         count++;
    //     }
    // }


    // ------------------------
    // mainloop
    // ------------------------

    public static void main() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        printOptions();
        while (running) {
            // TODO: wrap in try-catch
            int input = Integer.parseInt(scanner.nextLine().trim());
            switch (input) {
                case 1:
                    addStudent(scanner);
                    printOptions();
                    break;

                case 2:
                    addCourse(scanner);
                    printOptions();
                    break;

                case 3:
                    scanner.close();
                    running = false;
                    break;

                case 4:
                    selectStudent(scanner);
                    break;


                case 0:
                default:
                    printOptions();
                    break;
            }
        }
    }

    // ------------------------
    // main help menu
    // ------------------------

    public static void printOptions() {
        System.out.println("------");
        System.out.println("Hello, please select something :)");
        System.out.println("1: add student");
        System.out.println("2: add course");
        System.out.println("3: give up");
        System.out.println("0: print this help message");
        System.out.println("------");
    }

    // ------------------------
    // add basic objects
    // ------------------------

    public static void addStudent(Scanner scanner) {
        System.out.println("What is the students name?");
        String name = scanner.nextLine().trim();
        new Student(name);
        System.out.println("Successfully added the student!\n");
    }

    public static void addCourse(Scanner scanner) {
        System.out.println("What is the course name?");
        String name = scanner.nextLine().trim();
        new Course(name);
        System.out.println("Successfully added the course!\n");
    }

    public static void addGroup(Scanner scanner) {
        System.out.println("What is the group name?");
        String name = scanner.nextLine().trim();
        new Group(name);
        System.out.println("Successfully added the group!\n");
    }

    public static void addRoom(Scanner scanner) {
        System.out.println("What is the room name?");
        String name = scanner.nextLine().trim();
        new Room(name);
        System.out.println("Successfully added the room!\n");
    }
    // ------------------------
    // selectors for basic objects
    // ------------------------

    public static Student selectStudent(Scanner scanner) {
        System.out.println("ALL STUDENTS:");
        ArrayList<Student> allStudents = Student.getAll();
        int index = 1;
        for (Student student : allStudents) {
            System.out.println(index + ": " + student);
            index++;
        }

        int chosenIndex = -1;
        do {
            System.out.println("please select a student");
            chosenIndex = Integer.parseInt(scanner.nextLine().trim());
            // make sure index is valid
        } while (chosenIndex <= 0 || chosenIndex > Student.getCount());

        return allStudents.get(chosenIndex - 1);
    }

    public static Course selectCourse(Scanner scanner) {
        System.out.println("ALL COURSES:");
        ArrayList<Course> allCourses = Course.getAll();
        int index = 1;
        for (Course course : allCourses) {
            System.out.println(index + ": " + course);
            index++;
        }

        int chosenIndex = -1;
        do {
            System.out.println("please select a course");
            chosenIndex = Integer.parseInt(scanner.nextLine().trim());
            // make sure index is valid
        } while (chosenIndex <= 0 || chosenIndex > Course.getCount());

        return allCourses.get(chosenIndex - 1);
    }

    public static Group selectGroup(Scanner scanner) {
        System.out.println("ALL GROUPS:");
        ArrayList<Group> allGroups = Group.getAll();
        int index = 1;
        for (Group group : allGroups) {
            System.out.println(index + ": " + group);
            index++;
        }

        int chosenIndex = -1;
        do {
            System.out.println("please select a group");
            chosenIndex = Integer.parseInt(scanner.nextLine().trim());
            // make sure index is valid
        } while (chosenIndex <= 0 || chosenIndex > Group.getCount());

        return allGroups.get(chosenIndex - 1);
    }


    // ------------------------
    // link objects to one another
    // ------------------------

    public void addStudentToCourse(Scanner scanner) {
        Student student = selectStudent(scanner);
        Course course = selectCourse(scanner);
        student.addCourse(course);
        System.out.println("successfully added student to course");
    }

    public void addStudentToGroup(Scanner scanner) {
        Student student = selectStudent(scanner);
        Group group = selectGroup(scanner);
        group.addStudent(student);
        System.out.println("successfully added student to group");
    }
}
