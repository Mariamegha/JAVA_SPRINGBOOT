import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsUtilityDemo {
    public static void demonstrateUtilities() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(7);
        numbers.add(2);
        numbers.add(9);
        numbers.add(2);

        Collections.sort(numbers);
        System.out.println("Sorted list: " + numbers);

        int binarySearchIndex = Collections.binarySearch(numbers, 7);
        int frequency = Collections.frequency(numbers, 2);
        int minimum = Collections.min(numbers);
        int maximum = Collections.max(numbers);

        Collections.reverse(numbers);
        System.out.println("Reversed list: " + numbers);
        System.out.println("Minimum / maximum: " + minimum + " / " + maximum);
        System.out.println("Frequency of 2: " + frequency);
        System.out.println("Binary search index of 7 in the sorted list: "
                + binarySearchIndex);

        check(binarySearchIndex >= 0 && binarySearchIndex < numbers.size()
                        && numbers.get(numbers.size() - 1 - binarySearchIndex) == 7,
                "Binary search finds a value in the correctly sorted list");
        check(minimum == 2 && maximum == 9 && frequency == 2,
                "Collections min, max and frequency");
        check(numbers.get(0) == 9 && numbers.get(numbers.size() - 1) == 2,
                "Collections reverse");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
