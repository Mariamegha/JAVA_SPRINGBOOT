/**
 * Uses references to explain reachability without inspecting JVM memory.
 */
public class MemoryDemo {
    public static void demonstrate() {
        int localScore = 42;
        System.out.println("Stack concept: local primitive value localScore = " + localScore);

        Student firstReference = new Student(401, "Reachable Student");
        Student secondReference = firstReference;
        System.out.println("Heap concept: new created a Student object.");
        System.out.println("Local references firstReference and secondReference point to it.");
        check(firstReference == secondReference,
                "Two references can point to the same object.");

        firstReference = null;
        System.out.println("firstReference is now null; secondReference still reaches "
                + secondReference);
        check(secondReference != null
                        && "Reachable Student".equals(secondReference.getName()),
                "The object remains reachable while secondReference points to it.");

        secondReference = null;
        System.out.println("Both references to that Student are now null.");
        System.out.println("If no other references exist, that object may be GC-eligible.");

        Student unreferencedStudent = new Student(402, "Unreferenced Student");
        System.out.println("Created another object: " + unreferencedStudent);
        unreferencedStudent = null;
        System.out.println("Its only local reference was cleared; it may be GC-eligible.");

        System.out.println("GC eligibility does not mean collection happens immediately.");
        System.out.println("These labels explain a concept; they do not inspect physical memory.");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
