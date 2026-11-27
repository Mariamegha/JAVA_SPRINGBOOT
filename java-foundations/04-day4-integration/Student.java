/**
 * Represents one student and demonstrates instance and static members.
 */
public class Student {
    private static int studentCount;

    private final int id;
    private String name;

    static {
        System.out.println("[Initialization] Student class initialized; shared count = "
                + studentCount);
    }

    public Student(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }

        this.id = id;
        this.name = requireValidName(name);
        studentCount++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = requireValidName(name);
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static void displayStudentCount() {
        System.out.println("Students created: " + studentCount);
    }

    private static String requireValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or blank.");
        }
        return name.trim();
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
