import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SortingDemo {
    public static void demonstrateSorting() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(103, "Zara", "zara@example.com", 92));
        students.add(new Student(101, "Arun", "arun@example.com", 85));
        students.add(new Student(104, "Meera", "meera@example.com", 92));
        students.add(new Student(102, "Bala", "bala@example.com", 85));

        List<Student> byId = new ArrayList<>(students);
        byId.sort(null);
        System.out.println("Comparable natural order (ID): " + byId);

        List<Student> byName = new ArrayList<>(students);
        byName.sort(Comparator.comparing(Student::getName));
        System.out.println("Comparator by name: " + byName);

        List<Student> byMarks = new ArrayList<>(students);
        byMarks.sort(Comparator.comparingInt(Student::getMarks));
        System.out.println("Comparator by marks, ascending: " + byMarks);

        Comparator<Student> marksDescendingThenName = Comparator
                .comparingInt(Student::getMarks)
                .reversed()
                .thenComparing(Student::getName);
        List<Student> ranked = new ArrayList<>(students);
        ranked.sort(marksDescendingThenName);
        System.out.println("Marks descending, then name ascending: " + ranked);

        // A TreeSet uses comparison both for order and to decide whether
        // two elements are equivalent; the ID tie-breaker keeps each student.
        TreeSet<Student> rankedSet = new TreeSet<>(marksDescendingThenName
                .thenComparingInt(Student::getId));
        rankedSet.addAll(students);
        System.out.println("TreeSet with custom comparator: " + rankedSet);

        check(byId.get(0).getId() == 101 && byId.get(3).getId() == 104,
                "Comparable orders students by ID");
        check("Arun".equals(byName.get(0).getName())
                        && "Zara".equals(byName.get(3).getName()),
                "Comparator orders students by name");
        check(ranked.get(0).getMarks() == 92
                        && "Meera".equals(ranked.get(0).getName())
                        && ranked.get(1).getMarks() == 92
                        && "Zara".equals(ranked.get(1).getName()),
                "Comparator chaining orders by marks descending then name");
        check(rankedSet.size() == students.size(),
                "TreeSet comparator retains every student using ID tie-breaker");

        Student sameIdentity = new Student(101, "Different Name",
                "different@example.com", 70);
        check(students.get(1).equals(sameIdentity)
                        && students.get(1).hashCode() == sameIdentity.hashCode()
                        && students.get(1).compareTo(sameIdentity) == 0,
                "Student equality, hash code and natural ordering use ID identity");
        check(rejectsInvalidStudent(0, "Asha", "asha@example.com", 90)
                        && rejectsInvalidStudent(105, " ", "asha@example.com", 90)
                        && rejectsInvalidStudent(105, "Asha", "invalid-email", 90)
                        && rejectsInvalidStudent(105, "Asha", "asha@example.com", 101),
                "Student constructor rejects invalid ID, name, email and marks");
    }

    private static boolean rejectsInvalidStudent(int id, String name, String email, int marks) {
        try {
            new Student(id, name, email, marks);
            return false;
        } catch (IllegalArgumentException expected) {
            return true;
        }
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
