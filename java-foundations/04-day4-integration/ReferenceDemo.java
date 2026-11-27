/**
 * Demonstrates references, object changes, null checks and pass-by-value.
 */
public class ReferenceDemo {
    public static void demonstrateAssignmentAndMutation() {
        Student a = new Student(101, "Arun");
        Student b = a;

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        check(a == b, "a and b refer to the same Student object.");

        b.changeName("Arun Kumar");
        System.out.println("After changing the name through b:");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        check("Arun Kumar".equals(a.getName()),
                "Changing the shared object through b is visible through a.");
    }

    public static void demonstrateReassignment() {
        Student a = new Student(102, "Meera");
        Student b = a;

        b = new Student(103, "Nila");
        System.out.println("After assigning a new Student to b:");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        check(a != b && "Meera".equals(a.getName()) && "Nila".equals(b.getName()),
                "Reassigning b does not change the object still referenced by a.");
    }

    public static void demonstrateNullSafety() {
        Student student = null;
        System.out.println("Initial student reference: " + student);

        if (student == null) {
            System.out.println("The reference is null; no Student method will be called.");
        } else {
            System.out.println(student.getName());
        }
        check(student == null, "A null reference is checked before use.");

        student = new Student(104, "Sana");
        if (student != null) {
            System.out.println("After the null check: " + student.getName());
        }
        check(student != null && "Sana".equals(student.getName()),
                "A non-null reference can safely be used after checking it.");
    }

    public static void demonstratePassByValueMutation() {
        Student callerStudent = new Student(301, "Lina");
        System.out.println("Before method call: " + callerStudent);

        changeNameThroughParameter(callerStudent);

        System.out.println("After method call:  " + callerStudent);
        check("Lina Updated".equals(callerStudent.getName()),
                "Mutating the object through a parameter affects the caller's object.");
    }

    public static void demonstratePassByValueReassignment() {
        Student callerStudent = new Student(302, "Omar");
        Student originalReference = callerStudent;
        System.out.println("Before method call: " + callerStudent);

        reassignParameter(callerStudent);

        System.out.println("After method call:  " + callerStudent);
        check(callerStudent == originalReference
                        && "Omar".equals(callerStudent.getName()),
                "Reassigning a parameter does not change the caller's reference.");
    }

    private static void changeNameThroughParameter(Student studentParameter) {
        studentParameter.changeName("Lina Updated");
    }

    private static void reassignParameter(Student studentParameter) {
        studentParameter = new Student(303, "Temporary");
        System.out.println("Inside method after parameter reassignment: " + studentParameter);
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
