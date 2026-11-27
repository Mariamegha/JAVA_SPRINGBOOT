/**
 * Runs each Day 4 demonstration in a clear, independent section.
 */
public class Main {
    public static void main(String[] args) {
        runStaticDemonstration();
        runFinalDemonstration();

        heading("3. Reference assignment and mutation");
        ReferenceDemo.demonstrateAssignmentAndMutation();

        heading("4. Reference reassignment");
        ReferenceDemo.demonstrateReassignment();

        heading("5. Null safety");
        ReferenceDemo.demonstrateNullSafety();

        heading("6. Pass-by-value");
        ReferenceDemo.demonstratePassByValueMutation();
        ReferenceDemo.demonstratePassByValueReassignment();

        heading("7. Memory and GC eligibility");
        MemoryDemo.demonstrate();

        heading("All Day 4 demonstrations completed successfully");
    }

    private static void runStaticDemonstration() {
        heading("1. Static members and initialization order");
        System.out.println("Application: " + AppConstants.APPLICATION_NAME);
        System.out.println("Default course: " + AppConstants.DEFAULT_COURSE);
        System.out.println("Maximum students: " + AppConstants.MAX_STUDENTS);

        Student.displayStudentCount();
        int countBeforeCreation = Student.getStudentCount();
        Student firstStudent = new Student(1, "Asha");
        Student secondStudent = new Student(2, "Rahul");

        System.out.println("Instance 1: " + firstStudent);
        System.out.println("Instance 2: " + secondStudent);
        Student.displayStudentCount();
        check(Student.getStudentCount() == countBeforeCreation + 2,
                "The static count increases once for each constructed Student.");
        check(AppConstants.MAX_STUDENTS > 0,
                "The shared maximum-student constant has a valid positive value.");
    }

    private static void runFinalDemonstration() {
        heading("2. Final instance variable and final reference");

        Student finalReference = new Student(201, "Fixed Reference");
        int originalId = finalReference.getId();
        System.out.println("Before changing the name: " + finalReference);

        finalReference.changeName("Name Changed");
        System.out.println("After changing the name:  " + finalReference);
        check(finalReference.getId() == originalId,
                "The Student ID stays fixed after construction.");
        check("Name Changed".equals(finalReference.getName()),
                "A final reference can still be used to mutate its object's name.");

        check(rejectsStudent(0, "Invalid ID"),
                "Student rejects a non-positive ID.");
        check(rejectsStudent(202, "   "),
                "Student rejects a blank name.");
    }

    private static boolean rejectsStudent(int id, String name) {
        try {
            new Student(id, name);
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

    private static void heading(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
