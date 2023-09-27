package aau;

public class Student {
    static int count = 0;

    public String name;

    public Student(String name) {
        this.name = name;
        count++;
    }

    public static void greet() {
        System.out.println("I am a student");
    }

    public static int getCount() {
        return count;
    }
}
