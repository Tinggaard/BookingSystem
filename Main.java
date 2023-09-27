import aau.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Grouproom room = new Grouproom("hello");
        // System.out.println(room.getName());

        Student thomas = new Student("thomas");
        Student martin = new Student("martin");
        Student jens = new Student("jens");

        Group p3 = new Group("p3");
        p3.addStudent(thomas);
        p3.addStudent(martin);
        p3.addStudent(jens);

        Group p4 = new Group("p4");
        p4.addStudent(jens);

        List<Student> members = p3.getStudents();

        // for (Student student : members) {
        //     System.out.println(student.name);
        // }

        for (Student student : p4.getStudents()) {
            System.out.println(student.name);
        }


    }

    public static void Slp(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
