import aau.*;

// import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner scanner = new Scanner(System.in);
        // String name = scanner.nextLine();
        // System.out.println(name);
        // scanner.close();
        BookingSystem system = new BookingSystem();
        system.init();

        System.out.println(String.format("There are %d students", Student.getCount()));
        System.out.println(String.format("there are %d groups", Group.getGroupCount()));
    }
}
