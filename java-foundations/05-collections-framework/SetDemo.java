import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void demonstrateHashSet() {
        Set<String> colors = new HashSet<>();
        colors.add("blue");
        colors.add("green");
        boolean duplicateAdded = colors.add("blue");

        System.out.println("Unique colors (iteration order is not guaranteed): " + colors);
        System.out.println("Contains green: " + colors.contains("green"));
        System.out.println("Size after duplicate insertion: " + colors.size());

        boolean removed = colors.remove("green");

        check(!duplicateAdded && colors.size() == 1 && colors.contains("blue"),
                "HashSet rejects duplicates and tracks unique values");
        check(removed && !colors.contains("green"),
                "HashSet contains and remove");
    }

    public static void demonstrateTreeSet() {
        TreeSet<Integer> scores = new TreeSet<>();
        scores.add(40);
        scores.add(10);
        scores.add(30);
        scores.add(20);

        System.out.println("Naturally sorted scores: " + scores);
        System.out.println("First and last: " + scores.first() + ", " + scores.last());
        System.out.println("floor(25): " + scores.floor(25));
        System.out.println("ceiling(25): " + scores.ceiling(25));
        System.out.println("lower(20): " + scores.lower(20));
        System.out.println("higher(20): " + scores.higher(20));

        Comparator<Student> byNameThenId = Comparator.comparing(Student::getName)
                .thenComparingInt(Student::getId);
        TreeSet<Student> students = new TreeSet<>(byNameThenId);
        students.add(new Student(3, "Meera", "meera@example.com", 91));
        students.add(new Student(1, "Asha", "asha@example.com", 88));
        students.add(new Student(2, "Asha", "asha2@example.com", 95));

        System.out.println("Students ordered by name, then ID: " + students);

        check(scores.first() == 10 && scores.last() == 40
                        && scores.floor(25) == 20 && scores.ceiling(25) == 30
                        && scores.lower(20) == 10 && scores.higher(20) == 30,
                "TreeSet sorting and navigational methods");
        check(students.size() == 3 && students.first().getId() == 1
                        && students.last().getId() == 3,
                "TreeSet custom object ordering keeps distinct IDs");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
