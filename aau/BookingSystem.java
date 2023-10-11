package aau;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
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
                    addGroup(scanner);
                    printOptions();
                    break;

                case 4:
                    addRoom(scanner);
                    printOptions();
                    break;

                case 5:
                    addStudentToCourse(scanner);
                    printOptions();
                    break;

                case 6:
                    addStudentToGroup(scanner);
                    printOptions();
                    break;

                case 7:
                    reserveRoomForGroup(scanner);
                    printOptions();
                    break;

                // case 4:
                //     selectStudent(scanner);
                //     break;

                case 0:
                    scanner.close();
                    running = false;
                    break;

                case 8:
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
        System.out.println("3: add group");
        System.out.println("4: add room");
        System.out.println("5: add student to course");
        System.out.println("6: add student to group");
        System.out.println("7: reserve room for group");
        System.out.println("8: print this help message");
        System.out.println("0: exit");
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
        Course course = selectCourse(scanner);
        new Group(name, course);
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

    public static void addStudentToCourse(Scanner scanner) {
        Student student = selectStudent(scanner);
        Course course = selectCourse(scanner);
        student.addCourse(course);
        System.out.println("successfully added student to course");
    }

    public static void addStudentToGroup(Scanner scanner) {
        Student student = selectStudent(scanner);
        Group group = selectGroup(scanner);
        group.addStudent(student);
        System.out.println("successfully added student to group");
    }

    // ------------------------
    // interact with objects
    // ------------------------

    public static void reserveRoomForGroup(Scanner scanner) {
        Group group = selectGroup(scanner);
        boolean success = Room.reserveNextAvailable(group);
        if (!success) {
            System.out.print("Could not find available room, or group has already reserved a room\n");
        }
    }
}
