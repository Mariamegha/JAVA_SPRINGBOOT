import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void demonstratePriorityQueues() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(20);

        System.out.println("Min-heap head from peek: " + minHeap.peek());
        StringBuilder ascendingOrder = new StringBuilder();
        while (!minHeap.isEmpty()) {
            ascendingOrder.append(minHeap.poll()).append(' ');
        }
        System.out.println("Repeated poll gives priority order: " + ascendingOrder.toString().trim());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(20);
        StringBuilder descendingOrder = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            descendingOrder.append(maxHeap.poll()).append(' ');
        }
        System.out.println("Custom max-heap poll order: " + descendingOrder.toString().trim());

        Comparator<Student> highestMarksFirst = Comparator
                .comparingInt(Student::getMarks)
                .reversed()
                .thenComparing(Student::getName);
        PriorityQueue<Student> students = new PriorityQueue<>(highestMarksFirst);
        students.offer(new Student(1, "Asha", "asha@example.com", 88));
        students.offer(new Student(2, "Meera", "meera@example.com", 96));
        students.offer(new Student(3, "Rahul", "rahul@example.com", 91));

        StringBuilder studentPriority = new StringBuilder();
        while (!students.isEmpty()) {
            studentPriority.append(students.poll().getName()).append(' ');
        }
        System.out.println("Students polled by marks priority: "
                + studentPriority.toString().trim());

        check("10 20 30".equals(ascendingOrder.toString().trim()),
                "Default PriorityQueue polls minimum first");
        check("30 20 10".equals(descendingOrder.toString().trim()),
                "Comparator PriorityQueue polls maximum first");
        check("Meera Rahul Asha".equals(studentPriority.toString().trim()),
                "PriorityQueue orders custom objects by Comparator");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
