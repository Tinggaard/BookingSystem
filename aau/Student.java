package aau;

import java.util.Random;
import java.util.ArrayList;

public class Student {
    private static int count = 0;
    private static Random generator = new Random(1337);
    static ArrayList<Student> allStudents = new ArrayList<Student>();

    String name;
    private int attendingCourseCount = 0;
    int studentId = generator.nextInt(1000);
    ArrayList<Course> attendingCourses = new ArrayList<Course>();

    // constructors

    public Student(String name) {
        this.name = name;
        if (allStudents.contains(this)) {
            return;
        }
        allStudents.add(this);
        count++;
    }

    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
        if (allStudents.contains(this)) {
            return;
        }
        allStudents.add(this);
        count++;
    }

    // static methods

    public static int getCount() {
        return count;
    }

    public static ArrayList<Student> getAll() {
        return allStudents;
    }

    // overrides

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Student)) {
            return false;
        }
        return this.studentId == ((Student) other).studentId;
    }

    public String toString() {
        return this.name;
    }

    // methods

    public void addCourse(Course course) throws Error {
        if (this.attendingCourseCount >= 5) {
            throw new Error("Student cannot attend more than 5 courses");
        }
        if (this.attendingCourses.contains(course)) {
            throw new Error("Student already takes this course");
        }

        this.attendingCourses.add(course);
        this.attendingCourseCount++;
    }
}
