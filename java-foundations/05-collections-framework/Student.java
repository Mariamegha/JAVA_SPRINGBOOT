import java.util.Objects;

/**
 * A student whose identity is defined by a positive ID.
 */
public class Student implements Comparable<Student> {
    private final int id;
    private final String name;
    private final String email;
    private final int marks;

    public Student(int id, String name, String email, int marks) {
        String normalizedEmail = email == null ? "" : email.trim();
        int atIndex = normalizedEmail.indexOf('@');
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name must not be blank.");
        }
        if (atIndex <= 0 || atIndex != normalizedEmail.lastIndexOf('@')
                || atIndex == normalizedEmail.length() - 1) {
            throw new IllegalArgumentException("Student email must contain a valid @ separator.");
        }
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Student marks must be between 0 and 100.");
        }

        this.id = id;
        this.name = name.trim();
        this.email = normalizedEmail;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Student)) {
            return false;
        }
        Student student = (Student) other;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', email='" + email
                + "', marks=" + marks + "}";
    }
}
