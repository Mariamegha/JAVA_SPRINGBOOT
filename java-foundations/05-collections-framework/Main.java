/**
 * Runs each Day 5 collections demonstration in its own section.
 */
public class Main {
    public static void main(String[] args) {
        heading("1. ArrayList");
        ListDemo.demonstrateArrayList();

        heading("2. LinkedList");
        ListDemo.demonstrateLinkedList();

        heading("3. HashSet");
        SetDemo.demonstrateHashSet();

        heading("4. TreeSet");
        SetDemo.demonstrateTreeSet();

        heading("5. Comparable vs Comparator");
        SortingDemo.demonstrateSorting();

        heading("6. HashMap");
        MapDemo.demonstrateHashMap();

        heading("7. TreeMap");
        MapDemo.demonstrateTreeMap();

        heading("8. Queue");
        QueueDemo.demonstrateQueue();

        heading("9. Deque / ArrayDeque");
        QueueDemo.demonstrateDequeAndStack();

        heading("10. PriorityQueue");
        PriorityQueueDemo.demonstratePriorityQueues();

        heading("11. Iterator");
        IteratorDemo.demonstrateIterator();

        heading("12. Collections utility methods");
        CollectionsUtilityDemo.demonstrateUtilities();

        System.out.println("\nAll Day 5 demonstrations completed successfully.");
    }

    private static void heading(String title) {
        System.out.println("\n=== " + title + " ===");
    }
}
