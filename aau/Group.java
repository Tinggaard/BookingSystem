package aau;

import java.util.ArrayList;

public class Group {
    ArrayList<Student> studentsInCourse = new ArrayList<Student>();
    ArrayList<Group> allGroups = new ArrayList<Group>();
    static int groupCount = 0;
    int groupSize = 0;
    String id;
    Course subject;

    public Group(String id) {
        this.id = id;
        if (allGroups.contains(this)) {
            return;
        }
        allGroups.add(this);
        groupCount++;
    }

    public Group(String id, Course course) {

    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Group)) {
            return false;
        }
        return this.id.equals(((Group) other).id);
    }

    public static int getGroupCount() {
        return groupCount;
    }

    public void addStudent(Student student) throws Error {
        if (studentsInCourse.contains(student)) {
            throw new Error("Student already in this group");
        }

        if (getGroupSize() >= 6) {
            throw new Error("Maximum group size reached");
        }
        studentsInCourse.add(student);
        groupSize++;
    }

    public ArrayList<Student> getStudentsInCourse() {
        return studentsInCourse;
    }

    public int getGroupSize() {
        return groupSize;
    }
}
