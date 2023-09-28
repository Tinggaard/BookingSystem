package aau;

public class Student {
    static int count = 0;

    public String name;

    public Student(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }
}
