/**
 * Demonstrates creating and using Employee objects.
 */
public class Main {
    public static void main(String[] args) {
        Employee first = new Employee("Asha", "E101", 50000.0, "Engineering");
        Employee second = new Employee("Rahul", "E102", 45000.0, "Support");
        Employee third = new Employee("Meera", "E103", 52000.0, "Marketing");
        Employee defaultEmployee = new Employee();

        System.out.println("Initial employee details:");
        first.displayDetails();
        second.displayDetails();
        third.displayDetails();
        defaultEmployee.displayDetails();

        // Instance fields belong to each object, so this change affects only first.
        first.increaseSalary(5000.0);
        System.out.println("After increasing Asha's salary:");
        first.displayDetails();
        second.displayDetails();

        // This change also affects only one employee object.
        second.changeDepartment("Engineering");
        System.out.println("After changing Rahul's department:");
        second.displayDetails();
        third.displayDetails();

        // Test scenarios without external testing libraries.
        runTests(first, second, third, defaultEmployee);

        System.out.println("Total Employee objects created: "
                + Employee.getEmployeeCount());
    }

    private static void runTests(Employee first, Employee second,
                                 Employee third, Employee defaultEmployee) {
        if (first.getSalary() == 55000.0) {
            System.out.println("Test passed: salary increase updated only the first employee.");
        } else {
            System.out.println("Test failed: salary increase.");
        }

        if ("Engineering".equals(second.getDepartment())
                && "Marketing".equals(third.getDepartment())) {
            System.out.println("Test passed: department changes affect only the selected employee.");
        } else {
            System.out.println("Test failed: department change.");
        }

        if ("Unknown".equals(defaultEmployee.getName())
                && "E000".equals(defaultEmployee.getEmployeeId())) {
            System.out.println("Test passed: no-argument constructor uses constructor chaining.");
        } else {
            System.out.println("Test failed: no-argument constructor.");
        }

        if (Employee.getEmployeeCount() == 4) {
            System.out.println("Test passed: four Employee objects were created.");
        } else {
            System.out.println("Test failed: employee count.");
        }

        System.out.println();
    }
}
