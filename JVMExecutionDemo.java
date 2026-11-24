/**
 * Demonstrates core concepts involved in executing Java bytecode.
 */
public class JVMExecutionDemo {
    private static int staticCallCount;

    private final String instanceName;

    public JVMExecutionDemo(String instanceName) {
        this.instanceName = instanceName;
    }

    public void demonstrateMethodCalls() {
        int first = 10;
        int second = 15;

        // Method arguments and the local variables first/second are stored in
        // the current method frame on the JVM stack.
        int result = add(first, second);

        System.out.println("Method call result: " + result);
        System.out.println("Local variables first=" + first + ", second=" + second);
        System.out.println();
    }

    private int add(int a, int b) {
        int sum = a + b;

        // add() executes in a separate frame. When it returns, sum and the
        // other frame-local values are discarded from that frame.
        return sum;
    }

    public void demonstrateObjectAndHeapReferences() {
        Person person = createPerson("Asha", 22);

        // person is a reference held by a local variable; the Person object
        // itself is allocated on the heap and remains reachable through person.
        System.out.println("Heap object: " + person);
        System.out.println("Stack reference variable name: person");
        System.out.println("Returned heap object fields: " + person.getName()
                + ", " + person.getAge());
        System.out.println();
    }

    private Person createPerson(String name, int age) {
        Person person = new Person(name, age);

        // The object is created on the heap; a reference to it is returned
        // and assigned to the local variable in the calling method.
        return person;
    }

    public void demonstrateStaticAndInstanceMembers() {
        staticCallCount++;

        System.out.println("Static member staticCallCount: " + staticCallCount);
        System.out.println("Instance member instanceName: " + instanceName);
        System.out.println();
    }

    private static class Person {
        private final String name;
        private final int age;

        private Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String getName() {
            return name;
        }

        private int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
}
