/**
 * Represents an employee and demonstrates class state and behavior.
 */
public class Employee {
    private static int employeeCount;

    private String name;
    private String employeeId;
    private double salary;
    private String department;

    public Employee(String name, String employeeId, double salary, String department) {
        this.name = name;
        this.employeeId = employeeId;
        this.salary = salary;
        this.department = department;
        employeeCount++;
    }

    public Employee() {
        this("Unknown", "E000", 0.0, "Unassigned");
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Salary: " + salary);
        System.out.println("Department: " + department);
        System.out.println();
    }

    public void increaseSalary(double amount) {
        if (amount > 0) {
            salary += amount;
        }
    }

    public void changeDepartment(String department) {
        this.department = department;
    }
}
