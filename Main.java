/**
 * Entry point for the JVM execution demonstration.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Java Backend Engineering Journey");
        System.out.println("Module: JVM, JDK, JRE and Java execution pipeline");
        System.out.println();

        JVMExecutionDemo demo = new JVMExecutionDemo("Instance demonstration");

        demo.demonstrateMethodCalls();
        demo.demonstrateObjectAndHeapReferences();
        demo.demonstrateStaticAndInstanceMembers();
    }
}
