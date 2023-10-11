package aau;

import java.util.ArrayList;

public class Group {
    static ArrayList<Group> allGroups = new ArrayList<Group>();
    static int count = 0;

    ArrayList<Student> students = new ArrayList<Student>();
    int groupSize = 0;
    String id;
    Course subject;

    // constructors

    public Group(String id, Course course) {
        this.id = id;
        this.subject = course;
        if (allGroups.contains(this)) {
            return;
        }

        allGroups.add(this);
        count++;
    }

    // static methods

    public static int getCount() {
        return count;
    }

    public static ArrayList<Group> getAll() {
        return allGroups;
    }

    // overrides

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Group)) {
            return false;
        }
        return this.id.equals(((Group) other).id);
    }

    public String toString() {
        return this.id;
    }

    // getters

    public ArrayList<Student> getStudentsInCourse() {
        return students;
    }

    public int getGroupSize() {
        return groupSize;
    }

    // methods

    public void addStudent(Student student) throws Error {
        if (students.contains(student)) {
            throw new Error("Student already in this group");
        }

        if (getGroupSize() >= 6) {
            throw new Error("Maximum group size reached");
        }
        students.add(student);
        groupSize++;
    }
}
