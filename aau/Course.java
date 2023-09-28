package aau;

import java.util.ArrayList;

public class Course {
    String subject;
    ArrayList<Student> attendees = new ArrayList<Student>();
    static ArrayList<Course> allCourses = new ArrayList<Course>();

    public Course(String subject) {
        this.subject = subject;
        if (allCourses.contains(this)) {
            // throw new Error("Course already exists");
            return;
        }
        allCourses.add(this);
    }

    public static void addCourses(Iterable<String> subjects) {
        for (String subject : subjects) {
            new Course(subject);
        }
    }

    // override equals to work on ArrayList.contains.
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Course)) {
            return false;
        }
        return this.subject.equals(((Course) other).subject);
    }

    public String toString() {
        return this.subject;
    }

    public static Course getCourse(String subject) {
        int index = allCourses.indexOf(new Course(subject));
        if (index == -1) {
            return null;
        }
        return allCourses.get(index);
    }

    public static ArrayList<Course> getAllCourses() {
        return allCourses;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Student> getAttendees() {
        return this.attendees;
    }


    // add student to course, if they are not already in it.
    public void addStudent(Student student) throws Error {
        if (this.attendees.contains(student)) {
            throw new Error("Student already attends course");
        }
        this.attendees.add(student);
    }
}
