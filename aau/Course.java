package aau;

import java.util.ArrayList;

public class Course {
    static ArrayList<Course> allCourses = new ArrayList<Course>();
    static int count = 0;

    String subject;

    // constructors

    public Course(String subject) {
        this.subject = subject;
        if (allCourses.contains(this)) {
            // throw new Error("Course already exists");
            return;
        }
        allCourses.add(this);
        count++;
    }

    // static methods

    public static Course getCourse(String subject) {
        int index = allCourses.indexOf(new Course(subject));
        if (index == -1) {
            return null;
        }
        return allCourses.get(index);
    }

    public static ArrayList<Course> getAll() {
        return allCourses;
    }

    public static int getCount() {
        return count;
    }


    // overrides

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


    // getters

    public String getSubject() {
        return subject;
    }
}
