package aau;

import java.util.Random;
import java.util.ArrayList;

public class Student {
    static int count = 0;
    static Random generator = new Random(1337);
    static ArrayList<Student> allStudents = new ArrayList<Student>();
    String name;
    int studentId = generator.nextInt(1000);

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

    public static int getCount() {
        return count;
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Student)) {
            return false;
        }
        return this.studentId == ((Student) other).studentId;
    }

    public String toString() {
        return this.name;
    }
}
