import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListDemo {
    public static void demonstrateArrayList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(1, 15);
        numbers.set(2, 18);

        boolean removedByValue = numbers.remove(Integer.valueOf(30));
        Integer removedByIndex = numbers.remove(0);

        // A list's size is its element count; ArrayList may reserve extra capacity
        // internally so it can grow without reallocating for every added element.
        numbers.add(18);

        System.out.println("Elements: " + numbers);
        System.out.println("First element: " + numbers.get(0));
        System.out.println("Size: " + numbers.size());
        System.out.println("Contains 18: " + numbers.contains(18));
        System.out.println("Index of 18: " + numbers.indexOf(18));
        System.out.println("Duplicate 18 is allowed: " + numbers);

        for (Integer number : numbers) {
            System.out.println("Visited: " + number);
        }

        check(removedByValue && Integer.valueOf(10).equals(removedByIndex),
                "ArrayList value and index removals");
        check(numbers.size() == 3 && numbers.get(0) == 15 && numbers.get(1) == 18
                        && numbers.get(2) == 18,
                "ArrayList insertion, update, access and duplicate elements");
        check(numbers.contains(18) && numbers.indexOf(18) == 1,
                "ArrayList contains and indexOf");
    }

    public static void demonstrateLinkedList() {
        LinkedList<String> names = new LinkedList<>();
        names.add("middle");
        names.addFirst("first");
        names.addLast("last");

        System.out.println("As a List: " + names);
        System.out.println("First and last: " + names.getFirst() + ", " + names.getLast());

        String removedFirst = names.removeFirst();
        String removedLast = names.removeLast();

        List<String> listView = names;
        listView.add(0, "updated through List");

        Queue<String> queueView = names;
        queueView.offer("queued");
        String queueHead = queueView.peek();
        String removedFromQueue = queueView.poll();

        System.out.println("As a Queue after end operations: " + names);
        System.out.println("Queue head before poll: " + queueHead);

        check("first".equals(removedFirst) && "last".equals(removedLast),
                "LinkedList removes from both ends");
        check("updated through List".equals(queueHead)
                        && "updated through List".equals(removedFromQueue),
                "LinkedList works through List and Queue interfaces");
        check(names.contains("middle") && names.contains("queued"),
                "LinkedList retains the expected elements");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
