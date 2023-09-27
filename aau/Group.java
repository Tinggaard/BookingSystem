package aau;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public String name;
    public List<Student> students = new ArrayList<Student>();

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return this.students;
    }
}
