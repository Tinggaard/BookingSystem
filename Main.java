import aau.*;

public class Main {
    public static void main(String[] args) {

        BookingSystem.main();

        System.out.println(String.format("There are %d students", Student.getCount()));
        System.out.println(String.format("There are %d groups", Group.getCount()));
        System.out.println(String.format("There are %d courses", Course.getCount()));
        System.out.println(String.format("There are %d rooms", Room.getCount()));
    }
}
