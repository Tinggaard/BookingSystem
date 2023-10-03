import aau.BookingSystem;
import aau.Student;
import aau.Group;

public class Main {
    public static void main(String[] args) {

        BookingSystem.main();

        System.out.println(String.format("There are %d students", Student.getCount()));
        System.out.println(String.format("there are %d groups", Group.getCount()));
    }
}
