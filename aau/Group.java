package aau;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public String name;
    public List<Student> students = new ArrayList<Student>();
    public int count = 0;

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) throws Error {
        if (getGroupSize() >= 6)
        {
            throw new Error("Maximum group size reached");
        }
        students.add(student);
        count++;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getGroupSize() {
        return count;
    }
}
