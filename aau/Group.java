package aau;

import java.util.ArrayList;

public class Group {
    static ArrayList<Group> allGroups = new ArrayList<Group>();
    private static int count = 0;

    ArrayList<Student> students = new ArrayList<Student>();
    private int groupSize = 0;
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

    // methods

    public void addStudent(Student student) throws Error {
        if (this.students.contains(student)) {
            throw new Error("Student already in this group");
        }

        if (this.groupSize >= 6) {
            throw new Error("Maximum group size reached");
        }
        this.students.add(student);
        this.groupSize++;
    }
}
